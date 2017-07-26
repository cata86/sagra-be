package it.alecata.sagra.web.rest;

import it.alecata.sagra.SagrabeApp;

import it.alecata.sagra.domain.TavoloReale;
import it.alecata.sagra.domain.Sagra;
import it.alecata.sagra.repository.TavoloRealeRepository;
import it.alecata.sagra.service.TavoloRealeService;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TavoloRealeResource REST controller.
 *
 * @see TavoloRealeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SagrabeApp.class)
public class TavoloRealeResourceIntTest {

    private static final String DEFAULT_CODICE = "AAAAAAAAAA";
    private static final String UPDATED_CODICE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIZIONE = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIZIONE = "BBBBBBBBBB";

    private static final Integer DEFAULT_POSTI_MAX = 1;
    private static final Integer UPDATED_POSTI_MAX = 2;

    private static final Integer DEFAULT_POSTI_OCCUPATI = 1;
    private static final Integer UPDATED_POSTI_OCCUPATI = 2;

    private static final Boolean DEFAULT_ASPORTO = false;
    private static final Boolean UPDATED_ASPORTO = true;

    @Autowired
    private TavoloRealeRepository tavoloRealeRepository;

    @Autowired
    private TavoloRealeService tavoloRealeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTavoloRealeMockMvc;

    private TavoloReale tavoloReale;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TavoloRealeResource tavoloRealeResource = new TavoloRealeResource(tavoloRealeService);
        this.restTavoloRealeMockMvc = MockMvcBuilders.standaloneSetup(tavoloRealeResource)
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
    public static TavoloReale createEntity(EntityManager em) {
        TavoloReale tavoloReale = new TavoloReale()
            .codice(DEFAULT_CODICE)
            .descrizione(DEFAULT_DESCRIZIONE)
            .postiMax(DEFAULT_POSTI_MAX)
            .postiOccupati(DEFAULT_POSTI_OCCUPATI)
            .asporto(DEFAULT_ASPORTO);
        // Add required entity
        Sagra sagra = SagraResourceIntTest.createEntity(em);
        em.persist(sagra);
        em.flush();
        tavoloReale.setSagra(sagra);
        return tavoloReale;
    }

    @Before
    public void initTest() {
        tavoloReale = createEntity(em);
    }

    @Test
    @Transactional
    public void createTavoloReale() throws Exception {
        int databaseSizeBeforeCreate = tavoloRealeRepository.findAll().size();

        // Create the TavoloReale
        restTavoloRealeMockMvc.perform(post("/api/tavolo-reales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tavoloReale)))
            .andExpect(status().isCreated());

        // Validate the TavoloReale in the database
        List<TavoloReale> tavoloRealeList = tavoloRealeRepository.findAll();
        assertThat(tavoloRealeList).hasSize(databaseSizeBeforeCreate + 1);
        TavoloReale testTavoloReale = tavoloRealeList.get(tavoloRealeList.size() - 1);
        assertThat(testTavoloReale.getCodice()).isEqualTo(DEFAULT_CODICE);
        assertThat(testTavoloReale.getDescrizione()).isEqualTo(DEFAULT_DESCRIZIONE);
        assertThat(testTavoloReale.getPostiMax()).isEqualTo(DEFAULT_POSTI_MAX);
        assertThat(testTavoloReale.getPostiOccupati()).isEqualTo(DEFAULT_POSTI_OCCUPATI);
        assertThat(testTavoloReale.isAsporto()).isEqualTo(DEFAULT_ASPORTO);
    }

    @Test
    @Transactional
    public void createTavoloRealeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tavoloRealeRepository.findAll().size();

        // Create the TavoloReale with an existing ID
        tavoloReale.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTavoloRealeMockMvc.perform(post("/api/tavolo-reales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tavoloReale)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<TavoloReale> tavoloRealeList = tavoloRealeRepository.findAll();
        assertThat(tavoloRealeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTavoloReales() throws Exception {
        // Initialize the database
        tavoloRealeRepository.saveAndFlush(tavoloReale);

        // Get all the tavoloRealeList
        restTavoloRealeMockMvc.perform(get("/api/tavolo-reales?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tavoloReale.getId().intValue())))
            .andExpect(jsonPath("$.[*].codice").value(hasItem(DEFAULT_CODICE.toString())))
            .andExpect(jsonPath("$.[*].descrizione").value(hasItem(DEFAULT_DESCRIZIONE.toString())))
            .andExpect(jsonPath("$.[*].postiMax").value(hasItem(DEFAULT_POSTI_MAX)))
            .andExpect(jsonPath("$.[*].postiOccupati").value(hasItem(DEFAULT_POSTI_OCCUPATI)))
            .andExpect(jsonPath("$.[*].asporto").value(hasItem(DEFAULT_ASPORTO.booleanValue())));
    }

    @Test
    @Transactional
    public void getTavoloReale() throws Exception {
        // Initialize the database
        tavoloRealeRepository.saveAndFlush(tavoloReale);

        // Get the tavoloReale
        restTavoloRealeMockMvc.perform(get("/api/tavolo-reales/{id}", tavoloReale.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tavoloReale.getId().intValue()))
            .andExpect(jsonPath("$.codice").value(DEFAULT_CODICE.toString()))
            .andExpect(jsonPath("$.descrizione").value(DEFAULT_DESCRIZIONE.toString()))
            .andExpect(jsonPath("$.postiMax").value(DEFAULT_POSTI_MAX))
            .andExpect(jsonPath("$.postiOccupati").value(DEFAULT_POSTI_OCCUPATI))
            .andExpect(jsonPath("$.asporto").value(DEFAULT_ASPORTO.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTavoloReale() throws Exception {
        // Get the tavoloReale
        restTavoloRealeMockMvc.perform(get("/api/tavolo-reales/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTavoloReale() throws Exception {
        // Initialize the database
        tavoloRealeService.save(tavoloReale);

        int databaseSizeBeforeUpdate = tavoloRealeRepository.findAll().size();

        // Update the tavoloReale
        TavoloReale updatedTavoloReale = tavoloRealeRepository.findOne(tavoloReale.getId());
        updatedTavoloReale
            .codice(UPDATED_CODICE)
            .descrizione(UPDATED_DESCRIZIONE)
            .postiMax(UPDATED_POSTI_MAX)
            .postiOccupati(UPDATED_POSTI_OCCUPATI)
            .asporto(UPDATED_ASPORTO);

        restTavoloRealeMockMvc.perform(put("/api/tavolo-reales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTavoloReale)))
            .andExpect(status().isOk());

        // Validate the TavoloReale in the database
        List<TavoloReale> tavoloRealeList = tavoloRealeRepository.findAll();
        assertThat(tavoloRealeList).hasSize(databaseSizeBeforeUpdate);
        TavoloReale testTavoloReale = tavoloRealeList.get(tavoloRealeList.size() - 1);
        assertThat(testTavoloReale.getCodice()).isEqualTo(UPDATED_CODICE);
        assertThat(testTavoloReale.getDescrizione()).isEqualTo(UPDATED_DESCRIZIONE);
        assertThat(testTavoloReale.getPostiMax()).isEqualTo(UPDATED_POSTI_MAX);
        assertThat(testTavoloReale.getPostiOccupati()).isEqualTo(UPDATED_POSTI_OCCUPATI);
        assertThat(testTavoloReale.isAsporto()).isEqualTo(UPDATED_ASPORTO);
    }

    @Test
    @Transactional
    public void updateNonExistingTavoloReale() throws Exception {
        int databaseSizeBeforeUpdate = tavoloRealeRepository.findAll().size();

        // Create the TavoloReale

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTavoloRealeMockMvc.perform(put("/api/tavolo-reales")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tavoloReale)))
            .andExpect(status().isCreated());

        // Validate the TavoloReale in the database
        List<TavoloReale> tavoloRealeList = tavoloRealeRepository.findAll();
        assertThat(tavoloRealeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTavoloReale() throws Exception {
        // Initialize the database
        tavoloRealeService.save(tavoloReale);

        int databaseSizeBeforeDelete = tavoloRealeRepository.findAll().size();

        // Get the tavoloReale
        restTavoloRealeMockMvc.perform(delete("/api/tavolo-reales/{id}", tavoloReale.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TavoloReale> tavoloRealeList = tavoloRealeRepository.findAll();
        assertThat(tavoloRealeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TavoloReale.class);
        TavoloReale tavoloReale1 = new TavoloReale();
        tavoloReale1.setId(1L);
        TavoloReale tavoloReale2 = new TavoloReale();
        tavoloReale2.setId(tavoloReale1.getId());
        assertThat(tavoloReale1).isEqualTo(tavoloReale2);
        tavoloReale2.setId(2L);
        assertThat(tavoloReale1).isNotEqualTo(tavoloReale2);
        tavoloReale1.setId(null);
        assertThat(tavoloReale1).isNotEqualTo(tavoloReale2);
    }
}
