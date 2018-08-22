package it.alecata.sagra.web.rest;

import it.alecata.sagra.SagrabeApp;

import it.alecata.sagra.domain.Pietanza;
import it.alecata.sagra.domain.PietanzaCategoria;
import it.alecata.sagra.repository.PietanzaRepository;
import it.alecata.sagra.service.PietanzaService;
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
 * Test class for the PietanzaResource REST controller.
 *
 * @see PietanzaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SagrabeApp.class)
public class PietanzaResourceIntTest {

    private static final String DEFAULT_CODICE = "AAAAAAAAAA";
    private static final String UPDATED_CODICE = "BBBBBBBBBB";

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIZIONE = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIZIONE = "BBBBBBBBBB";

    private static final Float DEFAULT_PREZZO = 1F;
    private static final Float UPDATED_PREZZO = 2F;

    private static final Integer DEFAULT_ORDINE = 1;
    private static final Integer UPDATED_ORDINE = 2;

    @Autowired
    private PietanzaRepository pietanzaRepository;

    @Autowired
    private PietanzaService pietanzaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPietanzaMockMvc;

    private Pietanza pietanza;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PietanzaResource pietanzaResource = new PietanzaResource(pietanzaService);
        this.restPietanzaMockMvc = MockMvcBuilders.standaloneSetup(pietanzaResource)
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
    public static Pietanza createEntity(EntityManager em) {
        Pietanza pietanza = new Pietanza()
            .codice(DEFAULT_CODICE)
            .nome(DEFAULT_NOME)
            .descrizione(DEFAULT_DESCRIZIONE)
            .prezzo(DEFAULT_PREZZO)
            .ordine(DEFAULT_ORDINE);
        // Add required entity
        PietanzaCategoria pietanzaCategoria = PietanzaCategoriaResourceIntTest.createEntity(em);
        em.persist(pietanzaCategoria);
        em.flush();
        pietanza.setPietanzaCategoria(pietanzaCategoria);
        return pietanza;
    }

    @Before
    public void initTest() {
        pietanza = createEntity(em);
    }

    @Test
    @Transactional
    public void createPietanza() throws Exception {
        int databaseSizeBeforeCreate = pietanzaRepository.findAll().size();

        // Create the Pietanza
        restPietanzaMockMvc.perform(post("/api/pietanzas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pietanza)))
            .andExpect(status().isCreated());

        // Validate the Pietanza in the database
        List<Pietanza> pietanzaList = pietanzaRepository.findAll();
        assertThat(pietanzaList).hasSize(databaseSizeBeforeCreate + 1);
        Pietanza testPietanza = pietanzaList.get(pietanzaList.size() - 1);
        assertThat(testPietanza.getCodice()).isEqualTo(DEFAULT_CODICE);
        assertThat(testPietanza.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testPietanza.getDescrizione()).isEqualTo(DEFAULT_DESCRIZIONE);
        assertThat(testPietanza.getPrezzo()).isEqualTo(DEFAULT_PREZZO);
        assertThat(testPietanza.getOrdine()).isEqualTo(DEFAULT_ORDINE);
    }

    @Test
    @Transactional
    public void createPietanzaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pietanzaRepository.findAll().size();

        // Create the Pietanza with an existing ID
        pietanza.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPietanzaMockMvc.perform(post("/api/pietanzas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pietanza)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Pietanza> pietanzaList = pietanzaRepository.findAll();
        assertThat(pietanzaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPietanzas() throws Exception {
        // Initialize the database
        pietanzaRepository.saveAndFlush(pietanza);

        // Get all the pietanzaList
        restPietanzaMockMvc.perform(get("/api/pietanzas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pietanza.getId().intValue())))
            .andExpect(jsonPath("$.[*].codice").value(hasItem(DEFAULT_CODICE.toString())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].descrizione").value(hasItem(DEFAULT_DESCRIZIONE.toString())))
            .andExpect(jsonPath("$.[*].prezzo").value(hasItem(DEFAULT_PREZZO.doubleValue())))
            .andExpect(jsonPath("$.[*].ordine").value(hasItem(DEFAULT_ORDINE)));
    }

    @Test
    @Transactional
    public void getPietanza() throws Exception {
        // Initialize the database
        pietanzaRepository.saveAndFlush(pietanza);

        // Get the pietanza
        restPietanzaMockMvc.perform(get("/api/pietanzas/{id}", pietanza.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pietanza.getId().intValue()))
            .andExpect(jsonPath("$.codice").value(DEFAULT_CODICE.toString()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.descrizione").value(DEFAULT_DESCRIZIONE.toString()))
            .andExpect(jsonPath("$.prezzo").value(DEFAULT_PREZZO.doubleValue()))
            .andExpect(jsonPath("$.ordine").value(DEFAULT_ORDINE));
    }

    @Test
    @Transactional
    public void getNonExistingPietanza() throws Exception {
        // Get the pietanza
        restPietanzaMockMvc.perform(get("/api/pietanzas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePietanza() throws Exception {
        // Initialize the database
        pietanzaService.save(pietanza);

        int databaseSizeBeforeUpdate = pietanzaRepository.findAll().size();

        // Update the pietanza
        Pietanza updatedPietanza = pietanzaRepository.findOne(pietanza.getId());
        updatedPietanza
            .codice(UPDATED_CODICE)
            .nome(UPDATED_NOME)
            .descrizione(UPDATED_DESCRIZIONE)
            .prezzo(UPDATED_PREZZO)
            .ordine(UPDATED_ORDINE);

        restPietanzaMockMvc.perform(put("/api/pietanzas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPietanza)))
            .andExpect(status().isOk());

        // Validate the Pietanza in the database
        List<Pietanza> pietanzaList = pietanzaRepository.findAll();
        assertThat(pietanzaList).hasSize(databaseSizeBeforeUpdate);
        Pietanza testPietanza = pietanzaList.get(pietanzaList.size() - 1);
        assertThat(testPietanza.getCodice()).isEqualTo(UPDATED_CODICE);
        assertThat(testPietanza.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testPietanza.getDescrizione()).isEqualTo(UPDATED_DESCRIZIONE);
        assertThat(testPietanza.getPrezzo()).isEqualTo(UPDATED_PREZZO);
        assertThat(testPietanza.getOrdine()).isEqualTo(UPDATED_ORDINE);
    }

    @Test
    @Transactional
    public void updateNonExistingPietanza() throws Exception {
        int databaseSizeBeforeUpdate = pietanzaRepository.findAll().size();

        // Create the Pietanza

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPietanzaMockMvc.perform(put("/api/pietanzas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pietanza)))
            .andExpect(status().isCreated());

        // Validate the Pietanza in the database
        List<Pietanza> pietanzaList = pietanzaRepository.findAll();
        assertThat(pietanzaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePietanza() throws Exception {
        // Initialize the database
        pietanzaService.save(pietanza);

        int databaseSizeBeforeDelete = pietanzaRepository.findAll().size();

        // Get the pietanza
        restPietanzaMockMvc.perform(delete("/api/pietanzas/{id}", pietanza.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Pietanza> pietanzaList = pietanzaRepository.findAll();
        assertThat(pietanzaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pietanza.class);
        Pietanza pietanza1 = new Pietanza();
        pietanza1.setId(1L);
        Pietanza pietanza2 = new Pietanza();
        pietanza2.setId(pietanza1.getId());
        assertThat(pietanza1).isEqualTo(pietanza2);
        pietanza2.setId(2L);
        assertThat(pietanza1).isNotEqualTo(pietanza2);
        pietanza1.setId(null);
        assertThat(pietanza1).isNotEqualTo(pietanza2);
    }
}
