package it.alecata.sagra.web.rest;

import it.alecata.sagra.SagrabeApp;

import it.alecata.sagra.domain.Serata;
import it.alecata.sagra.domain.Sagra;
import it.alecata.sagra.repository.SerataRepository;
import it.alecata.sagra.service.SerataService;
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
import java.time.LocalDate;
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
 * Test class for the SerataResource REST controller.
 *
 * @see SerataResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SagrabeApp.class)
public class SerataResourceIntTest {

    private static final String DEFAULT_CODICE = "AAAAAAAAAA";
    private static final String UPDATED_CODICE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIZIONE = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIZIONE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final ZonedDateTime DEFAULT_DATA_APERTURA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA_APERTURA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DATA_CHIUSURA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATA_CHIUSURA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_PERSONA_APERTURA = "AAAAAAAAAA";
    private static final String UPDATED_PERSONA_APERTURA = "BBBBBBBBBB";

    private static final String DEFAULT_PERSONA_CHIUSURA = "AAAAAAAAAA";
    private static final String UPDATED_PERSONA_CHIUSURA = "BBBBBBBBBB";

    @Autowired
    private SerataRepository serataRepository;

    @Autowired
    private SerataService serataService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSerataMockMvc;

    private Serata serata;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SerataResource serataResource = new SerataResource(serataService);
        this.restSerataMockMvc = MockMvcBuilders.standaloneSetup(serataResource)
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
    public static Serata createEntity(EntityManager em) {
        Serata serata = new Serata()
            .codice(DEFAULT_CODICE)
            .descrizione(DEFAULT_DESCRIZIONE)
            .data(DEFAULT_DATA)
            .dataApertura(DEFAULT_DATA_APERTURA)
            .dataChiusura(DEFAULT_DATA_CHIUSURA)
            .personaApertura(DEFAULT_PERSONA_APERTURA)
            .personaChiusura(DEFAULT_PERSONA_CHIUSURA);
        // Add required entity
        Sagra sagra = SagraResourceIntTest.createEntity(em);
        em.persist(sagra);
        em.flush();
        serata.setSagra(sagra);
        return serata;
    }

    @Before
    public void initTest() {
        serata = createEntity(em);
    }

    @Test
    @Transactional
    public void createSerata() throws Exception {
        int databaseSizeBeforeCreate = serataRepository.findAll().size();

        // Create the Serata
        restSerataMockMvc.perform(post("/api/seratas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serata)))
            .andExpect(status().isCreated());

        // Validate the Serata in the database
        List<Serata> serataList = serataRepository.findAll();
        assertThat(serataList).hasSize(databaseSizeBeforeCreate + 1);
        Serata testSerata = serataList.get(serataList.size() - 1);
        assertThat(testSerata.getCodice()).isEqualTo(DEFAULT_CODICE);
        assertThat(testSerata.getDescrizione()).isEqualTo(DEFAULT_DESCRIZIONE);
        assertThat(testSerata.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testSerata.getDataApertura()).isEqualTo(DEFAULT_DATA_APERTURA);
        assertThat(testSerata.getDataChiusura()).isEqualTo(DEFAULT_DATA_CHIUSURA);
        assertThat(testSerata.getPersonaApertura()).isEqualTo(DEFAULT_PERSONA_APERTURA);
        assertThat(testSerata.getPersonaChiusura()).isEqualTo(DEFAULT_PERSONA_CHIUSURA);
    }

    @Test
    @Transactional
    public void createSerataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = serataRepository.findAll().size();

        // Create the Serata with an existing ID
        serata.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSerataMockMvc.perform(post("/api/seratas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serata)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Serata> serataList = serataRepository.findAll();
        assertThat(serataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSeratas() throws Exception {
        // Initialize the database
        serataRepository.saveAndFlush(serata);

        // Get all the serataList
        restSerataMockMvc.perform(get("/api/seratas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(serata.getId().intValue())))
            .andExpect(jsonPath("$.[*].codice").value(hasItem(DEFAULT_CODICE.toString())))
            .andExpect(jsonPath("$.[*].descrizione").value(hasItem(DEFAULT_DESCRIZIONE.toString())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].dataApertura").value(hasItem(sameInstant(DEFAULT_DATA_APERTURA))))
            .andExpect(jsonPath("$.[*].dataChiusura").value(hasItem(sameInstant(DEFAULT_DATA_CHIUSURA))))
            .andExpect(jsonPath("$.[*].personaApertura").value(hasItem(DEFAULT_PERSONA_APERTURA.toString())))
            .andExpect(jsonPath("$.[*].personaChiusura").value(hasItem(DEFAULT_PERSONA_CHIUSURA.toString())));
    }

    @Test
    @Transactional
    public void getSerata() throws Exception {
        // Initialize the database
        serataRepository.saveAndFlush(serata);

        // Get the serata
        restSerataMockMvc.perform(get("/api/seratas/{id}", serata.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(serata.getId().intValue()))
            .andExpect(jsonPath("$.codice").value(DEFAULT_CODICE.toString()))
            .andExpect(jsonPath("$.descrizione").value(DEFAULT_DESCRIZIONE.toString()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.dataApertura").value(sameInstant(DEFAULT_DATA_APERTURA)))
            .andExpect(jsonPath("$.dataChiusura").value(sameInstant(DEFAULT_DATA_CHIUSURA)))
            .andExpect(jsonPath("$.personaApertura").value(DEFAULT_PERSONA_APERTURA.toString()))
            .andExpect(jsonPath("$.personaChiusura").value(DEFAULT_PERSONA_CHIUSURA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSerata() throws Exception {
        // Get the serata
        restSerataMockMvc.perform(get("/api/seratas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSerata() throws Exception {
        // Initialize the database
        serataService.save(serata);

        int databaseSizeBeforeUpdate = serataRepository.findAll().size();

        // Update the serata
        Serata updatedSerata = serataRepository.findOne(serata.getId());
        updatedSerata
            .codice(UPDATED_CODICE)
            .descrizione(UPDATED_DESCRIZIONE)
            .data(UPDATED_DATA)
            .dataApertura(UPDATED_DATA_APERTURA)
            .dataChiusura(UPDATED_DATA_CHIUSURA)
            .personaApertura(UPDATED_PERSONA_APERTURA)
            .personaChiusura(UPDATED_PERSONA_CHIUSURA);

        restSerataMockMvc.perform(put("/api/seratas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSerata)))
            .andExpect(status().isOk());

        // Validate the Serata in the database
        List<Serata> serataList = serataRepository.findAll();
        assertThat(serataList).hasSize(databaseSizeBeforeUpdate);
        Serata testSerata = serataList.get(serataList.size() - 1);
        assertThat(testSerata.getCodice()).isEqualTo(UPDATED_CODICE);
        assertThat(testSerata.getDescrizione()).isEqualTo(UPDATED_DESCRIZIONE);
        assertThat(testSerata.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testSerata.getDataApertura()).isEqualTo(UPDATED_DATA_APERTURA);
        assertThat(testSerata.getDataChiusura()).isEqualTo(UPDATED_DATA_CHIUSURA);
        assertThat(testSerata.getPersonaApertura()).isEqualTo(UPDATED_PERSONA_APERTURA);
        assertThat(testSerata.getPersonaChiusura()).isEqualTo(UPDATED_PERSONA_CHIUSURA);
    }

    @Test
    @Transactional
    public void updateNonExistingSerata() throws Exception {
        int databaseSizeBeforeUpdate = serataRepository.findAll().size();

        // Create the Serata

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSerataMockMvc.perform(put("/api/seratas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serata)))
            .andExpect(status().isCreated());

        // Validate the Serata in the database
        List<Serata> serataList = serataRepository.findAll();
        assertThat(serataList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSerata() throws Exception {
        // Initialize the database
        serataService.save(serata);

        int databaseSizeBeforeDelete = serataRepository.findAll().size();

        // Get the serata
        restSerataMockMvc.perform(delete("/api/seratas/{id}", serata.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Serata> serataList = serataRepository.findAll();
        assertThat(serataList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Serata.class);
        Serata serata1 = new Serata();
        serata1.setId(1L);
        Serata serata2 = new Serata();
        serata2.setId(serata1.getId());
        assertThat(serata1).isEqualTo(serata2);
        serata2.setId(2L);
        assertThat(serata1).isNotEqualTo(serata2);
        serata1.setId(null);
        assertThat(serata1).isNotEqualTo(serata2);
    }
}
