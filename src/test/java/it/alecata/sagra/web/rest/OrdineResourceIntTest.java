package it.alecata.sagra.web.rest;

import it.alecata.sagra.SagrabeApp;

import it.alecata.sagra.domain.Ordine;
import it.alecata.sagra.domain.TavoloAccomodato;
import it.alecata.sagra.repository.OrdineRepository;
import it.alecata.sagra.service.OrdineService;
import it.alecata.sagra.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static it.alecata.sagra.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the OrdineResource REST controller.
 *
 * @see OrdineResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SagrabeApp.class)
public class OrdineResourceIntTest {

    private static final String DEFAULT_PERSONA_ORDINE = "AAAAAAAAAA";
    private static final String UPDATED_PERSONA_ORDINE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_DATA_ORDINE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA_ORDINE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_NUMERO_COPERTI = 1;
    private static final Integer UPDATED_NUMERO_COPERTI = 2;

    private static final Float DEFAULT_TOTALE = 1F;
    private static final Float UPDATED_TOTALE = 2F;

    private static final Float DEFAULT_QUOTA_PERSONA = 1F;
    private static final Float UPDATED_QUOTA_PERSONA = 2F;

    private static final Boolean DEFAULT_ASPORTO = false;
    private static final Boolean UPDATED_ASPORTO = true;

    @Autowired
    private OrdineRepository ordineRepository;

    @Autowired
    private OrdineService ordineService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOrdineMockMvc;

    private Ordine ordine;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OrdineResource ordineResource = new OrdineResource(ordineService);
        this.restOrdineMockMvc = MockMvcBuilders.standaloneSetup(ordineResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ordine createEntity(EntityManager em) {
        Ordine ordine = new Ordine()
            .personaOrdine(DEFAULT_PERSONA_ORDINE)
            .dataOrdine(DEFAULT_DATA_ORDINE)
            .numeroCoperti(DEFAULT_NUMERO_COPERTI)
            .totale(DEFAULT_TOTALE)
            .quotaPersona(DEFAULT_QUOTA_PERSONA)
            .asporto(DEFAULT_ASPORTO);
        // Add required entity
        TavoloAccomodato tavoloAccomodato = TavoloAccomodatoResourceIntTest.createEntity(em);
        em.persist(tavoloAccomodato);
        em.flush();
        ordine.setTavoloAccomodato(tavoloAccomodato);
        return ordine;
    }

    @Before
    public void initTest() {
        ordine = createEntity(em);
    }

    @Test
    @Transactional
    public void createOrdine() throws Exception {
        int databaseSizeBeforeCreate = ordineRepository.findAll().size();

        // Create the Ordine
        restOrdineMockMvc.perform(post("/api/ordines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ordine)))
            .andExpect(status().isCreated());

        // Validate the Ordine in the database
        List<Ordine> ordineList = ordineRepository.findAll();
        assertThat(ordineList).hasSize(databaseSizeBeforeCreate + 1);
        Ordine testOrdine = ordineList.get(ordineList.size() - 1);
        assertThat(testOrdine.getPersonaOrdine()).isEqualTo(DEFAULT_PERSONA_ORDINE);
        assertThat(testOrdine.getDataOrdine()).isEqualTo(DEFAULT_DATA_ORDINE);
        assertThat(testOrdine.getNumeroCoperti()).isEqualTo(DEFAULT_NUMERO_COPERTI);
        assertThat(testOrdine.getTotale()).isEqualTo(DEFAULT_TOTALE);
        assertThat(testOrdine.getQuotaPersona()).isEqualTo(DEFAULT_QUOTA_PERSONA);
        assertThat(testOrdine.isAsporto()).isEqualTo(DEFAULT_ASPORTO);
    }

    @Test
    @Transactional
    public void createOrdineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ordineRepository.findAll().size();

        // Create the Ordine with an existing ID
        ordine.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrdineMockMvc.perform(post("/api/ordines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ordine)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Ordine> ordineList = ordineRepository.findAll();
        assertThat(ordineList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOrdines() throws Exception {
        // Initialize the database
        ordineRepository.saveAndFlush(ordine);

        // Get all the ordineList
        restOrdineMockMvc.perform(get("/api/ordines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ordine.getId().intValue())))
            .andExpect(jsonPath("$.[*].personaOrdine").value(hasItem(DEFAULT_PERSONA_ORDINE.toString())))
            .andExpect(jsonPath("$.[*].dataOrdine").value(hasItem(sameInstant(DEFAULT_DATA_ORDINE))))
            .andExpect(jsonPath("$.[*].numeroCoperti").value(hasItem(DEFAULT_NUMERO_COPERTI)))
            .andExpect(jsonPath("$.[*].totale").value(hasItem(DEFAULT_TOTALE.doubleValue())))
            .andExpect(jsonPath("$.[*].quotaPersona").value(hasItem(DEFAULT_QUOTA_PERSONA.doubleValue())))
            .andExpect(jsonPath("$.[*].asporto").value(hasItem(DEFAULT_ASPORTO.booleanValue())));
    }

    @Test
    @Transactional
    public void getOrdine() throws Exception {
        // Initialize the database
        ordineRepository.saveAndFlush(ordine);

        // Get the ordine
        restOrdineMockMvc.perform(get("/api/ordines/{id}", ordine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ordine.getId().intValue()))
            .andExpect(jsonPath("$.personaOrdine").value(DEFAULT_PERSONA_ORDINE.toString()))
            .andExpect(jsonPath("$.dataOrdine").value(sameInstant(DEFAULT_DATA_ORDINE)))
            .andExpect(jsonPath("$.numeroCoperti").value(DEFAULT_NUMERO_COPERTI))
            .andExpect(jsonPath("$.totale").value(DEFAULT_TOTALE.doubleValue()))
            .andExpect(jsonPath("$.quotaPersona").value(DEFAULT_QUOTA_PERSONA.doubleValue()))
            .andExpect(jsonPath("$.asporto").value(DEFAULT_ASPORTO.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingOrdine() throws Exception {
        // Get the ordine
        restOrdineMockMvc.perform(get("/api/ordines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrdine() throws Exception {
        // Initialize the database
        ordineService.save(ordine);

        int databaseSizeBeforeUpdate = ordineRepository.findAll().size();

        // Update the ordine
        Ordine updatedOrdine = ordineRepository.findOne(ordine.getId());
        updatedOrdine
            .personaOrdine(UPDATED_PERSONA_ORDINE)
            .dataOrdine(UPDATED_DATA_ORDINE)
            .numeroCoperti(UPDATED_NUMERO_COPERTI)
            .totale(UPDATED_TOTALE)
            .quotaPersona(UPDATED_QUOTA_PERSONA)
            .asporto(UPDATED_ASPORTO);

        restOrdineMockMvc.perform(put("/api/ordines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOrdine)))
            .andExpect(status().isOk());

        // Validate the Ordine in the database
        List<Ordine> ordineList = ordineRepository.findAll();
        assertThat(ordineList).hasSize(databaseSizeBeforeUpdate);
        Ordine testOrdine = ordineList.get(ordineList.size() - 1);
        assertThat(testOrdine.getPersonaOrdine()).isEqualTo(UPDATED_PERSONA_ORDINE);
        assertThat(testOrdine.getDataOrdine()).isEqualTo(UPDATED_DATA_ORDINE);
        assertThat(testOrdine.getNumeroCoperti()).isEqualTo(UPDATED_NUMERO_COPERTI);
        assertThat(testOrdine.getTotale()).isEqualTo(UPDATED_TOTALE);
        assertThat(testOrdine.getQuotaPersona()).isEqualTo(UPDATED_QUOTA_PERSONA);
        assertThat(testOrdine.isAsporto()).isEqualTo(UPDATED_ASPORTO);
    }

    @Test
    @Transactional
    public void updateNonExistingOrdine() throws Exception {
        int databaseSizeBeforeUpdate = ordineRepository.findAll().size();

        // Create the Ordine

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restOrdineMockMvc.perform(put("/api/ordines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ordine)))
            .andExpect(status().isCreated());

        // Validate the Ordine in the database
        List<Ordine> ordineList = ordineRepository.findAll();
        assertThat(ordineList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteOrdine() throws Exception {
        // Initialize the database
        ordineService.save(ordine);

        int databaseSizeBeforeDelete = ordineRepository.findAll().size();

        // Get the ordine
        restOrdineMockMvc.perform(delete("/api/ordines/{id}", ordine.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Ordine> ordineList = ordineRepository.findAll();
        assertThat(ordineList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ordine.class);
        Ordine ordine1 = new Ordine();
        ordine1.setId(1L);
        Ordine ordine2 = new Ordine();
        ordine2.setId(ordine1.getId());
        assertThat(ordine1).isEqualTo(ordine2);
        ordine2.setId(2L);
        assertThat(ordine1).isNotEqualTo(ordine2);
        ordine1.setId(null);
        assertThat(ordine1).isNotEqualTo(ordine2);
    }
}
