package it.alecata.sagra.web.rest;

import it.alecata.sagra.SagrabeApp;

import it.alecata.sagra.domain.PietanzaOrdinata;
import it.alecata.sagra.domain.Ordine;
import it.alecata.sagra.domain.Pietanza;
import it.alecata.sagra.repository.PietanzaOrdinataRepository;
import it.alecata.sagra.service.PietanzaOrdinataService;
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
 * Test class for the PietanzaOrdinataResource REST controller.
 *
 * @see PietanzaOrdinataResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SagrabeApp.class)
public class PietanzaOrdinataResourceIntTest {

    private static final Integer DEFAULT_NUMERO_SEQUENZA = 1;
    private static final Integer UPDATED_NUMERO_SEQUENZA = 2;

    private static final Integer DEFAULT_QUANTITA = 1;
    private static final Integer UPDATED_QUANTITA = 2;

    private static final String DEFAULT_NOTE = "AAAAAAAAAA";
    private static final String UPDATED_NOTE = "BBBBBBBBBB";

    @Autowired
    private PietanzaOrdinataRepository pietanzaOrdinataRepository;

    @Autowired
    private PietanzaOrdinataService pietanzaOrdinataService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPietanzaOrdinataMockMvc;

    private PietanzaOrdinata pietanzaOrdinata;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PietanzaOrdinataResource pietanzaOrdinataResource = new PietanzaOrdinataResource(pietanzaOrdinataService);
        this.restPietanzaOrdinataMockMvc = MockMvcBuilders.standaloneSetup(pietanzaOrdinataResource)
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
    public static PietanzaOrdinata createEntity(EntityManager em) {
        PietanzaOrdinata pietanzaOrdinata = new PietanzaOrdinata()
            .numeroSequenza(DEFAULT_NUMERO_SEQUENZA)
            .quantita(DEFAULT_QUANTITA)
            .note(DEFAULT_NOTE);
        // Add required entity
        Ordine ordine = OrdineResourceIntTest.createEntity(em);
        em.persist(ordine);
        em.flush();
        pietanzaOrdinata.setOrdine(ordine);
        // Add required entity
        Pietanza pietanza = PietanzaResourceIntTest.createEntity(em);
        em.persist(pietanza);
        em.flush();
        pietanzaOrdinata.setPietanza(pietanza);
        return pietanzaOrdinata;
    }

    @Before
    public void initTest() {
        pietanzaOrdinata = createEntity(em);
    }

    @Test
    @Transactional
    public void createPietanzaOrdinata() throws Exception {
        int databaseSizeBeforeCreate = pietanzaOrdinataRepository.findAll().size();

        // Create the PietanzaOrdinata
        restPietanzaOrdinataMockMvc.perform(post("/api/pietanza-ordinatas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pietanzaOrdinata)))
            .andExpect(status().isCreated());

        // Validate the PietanzaOrdinata in the database
        List<PietanzaOrdinata> pietanzaOrdinataList = pietanzaOrdinataRepository.findAll();
        assertThat(pietanzaOrdinataList).hasSize(databaseSizeBeforeCreate + 1);
        PietanzaOrdinata testPietanzaOrdinata = pietanzaOrdinataList.get(pietanzaOrdinataList.size() - 1);
        assertThat(testPietanzaOrdinata.getNumeroSequenza()).isEqualTo(DEFAULT_NUMERO_SEQUENZA);
        assertThat(testPietanzaOrdinata.getQuantita()).isEqualTo(DEFAULT_QUANTITA);
        assertThat(testPietanzaOrdinata.getNote()).isEqualTo(DEFAULT_NOTE);
    }

    @Test
    @Transactional
    public void createPietanzaOrdinataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pietanzaOrdinataRepository.findAll().size();

        // Create the PietanzaOrdinata with an existing ID
        pietanzaOrdinata.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPietanzaOrdinataMockMvc.perform(post("/api/pietanza-ordinatas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pietanzaOrdinata)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<PietanzaOrdinata> pietanzaOrdinataList = pietanzaOrdinataRepository.findAll();
        assertThat(pietanzaOrdinataList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPietanzaOrdinatas() throws Exception {
        // Initialize the database
        pietanzaOrdinataRepository.saveAndFlush(pietanzaOrdinata);

        // Get all the pietanzaOrdinataList
        restPietanzaOrdinataMockMvc.perform(get("/api/pietanza-ordinatas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pietanzaOrdinata.getId().intValue())))
            .andExpect(jsonPath("$.[*].numeroSequenza").value(hasItem(DEFAULT_NUMERO_SEQUENZA.toString())))
            .andExpect(jsonPath("$.[*].quantita").value(hasItem(DEFAULT_QUANTITA)))
            .andExpect(jsonPath("$.[*].note").value(hasItem(DEFAULT_NOTE.toString())));
    }

    @Test
    @Transactional
    public void getPietanzaOrdinata() throws Exception {
        // Initialize the database
        pietanzaOrdinataRepository.saveAndFlush(pietanzaOrdinata);

        // Get the pietanzaOrdinata
        restPietanzaOrdinataMockMvc.perform(get("/api/pietanza-ordinatas/{id}", pietanzaOrdinata.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pietanzaOrdinata.getId().intValue()))
            .andExpect(jsonPath("$.numeroSequenza").value(DEFAULT_NUMERO_SEQUENZA.toString()))
            .andExpect(jsonPath("$.quantita").value(DEFAULT_QUANTITA))
            .andExpect(jsonPath("$.note").value(DEFAULT_NOTE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPietanzaOrdinata() throws Exception {
        // Get the pietanzaOrdinata
        restPietanzaOrdinataMockMvc.perform(get("/api/pietanza-ordinatas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePietanzaOrdinata() throws Exception {
        // Initialize the database
        pietanzaOrdinataService.save(pietanzaOrdinata);

        int databaseSizeBeforeUpdate = pietanzaOrdinataRepository.findAll().size();

        // Update the pietanzaOrdinata
        PietanzaOrdinata updatedPietanzaOrdinata = pietanzaOrdinataRepository.findOne(pietanzaOrdinata.getId());
        updatedPietanzaOrdinata
            .numeroSequenza(UPDATED_NUMERO_SEQUENZA)
            .quantita(UPDATED_QUANTITA)
            .note(UPDATED_NOTE);

        restPietanzaOrdinataMockMvc.perform(put("/api/pietanza-ordinatas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPietanzaOrdinata)))
            .andExpect(status().isOk());

        // Validate the PietanzaOrdinata in the database
        List<PietanzaOrdinata> pietanzaOrdinataList = pietanzaOrdinataRepository.findAll();
        assertThat(pietanzaOrdinataList).hasSize(databaseSizeBeforeUpdate);
        PietanzaOrdinata testPietanzaOrdinata = pietanzaOrdinataList.get(pietanzaOrdinataList.size() - 1);
        assertThat(testPietanzaOrdinata.getNumeroSequenza()).isEqualTo(UPDATED_NUMERO_SEQUENZA);
        assertThat(testPietanzaOrdinata.getQuantita()).isEqualTo(UPDATED_QUANTITA);
        assertThat(testPietanzaOrdinata.getNote()).isEqualTo(UPDATED_NOTE);
    }

    @Test
    @Transactional
    public void updateNonExistingPietanzaOrdinata() throws Exception {
        int databaseSizeBeforeUpdate = pietanzaOrdinataRepository.findAll().size();

        // Create the PietanzaOrdinata

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPietanzaOrdinataMockMvc.perform(put("/api/pietanza-ordinatas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pietanzaOrdinata)))
            .andExpect(status().isCreated());

        // Validate the PietanzaOrdinata in the database
        List<PietanzaOrdinata> pietanzaOrdinataList = pietanzaOrdinataRepository.findAll();
        assertThat(pietanzaOrdinataList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePietanzaOrdinata() throws Exception {
        // Initialize the database
        pietanzaOrdinataService.save(pietanzaOrdinata);

        int databaseSizeBeforeDelete = pietanzaOrdinataRepository.findAll().size();

        // Get the pietanzaOrdinata
        restPietanzaOrdinataMockMvc.perform(delete("/api/pietanza-ordinatas/{id}", pietanzaOrdinata.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PietanzaOrdinata> pietanzaOrdinataList = pietanzaOrdinataRepository.findAll();
        assertThat(pietanzaOrdinataList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PietanzaOrdinata.class);
        PietanzaOrdinata pietanzaOrdinata1 = new PietanzaOrdinata();
        pietanzaOrdinata1.setId(1L);
        PietanzaOrdinata pietanzaOrdinata2 = new PietanzaOrdinata();
        pietanzaOrdinata2.setId(pietanzaOrdinata1.getId());
        assertThat(pietanzaOrdinata1).isEqualTo(pietanzaOrdinata2);
        pietanzaOrdinata2.setId(2L);
        assertThat(pietanzaOrdinata1).isNotEqualTo(pietanzaOrdinata2);
        pietanzaOrdinata1.setId(null);
        assertThat(pietanzaOrdinata1).isNotEqualTo(pietanzaOrdinata2);
    }
}
