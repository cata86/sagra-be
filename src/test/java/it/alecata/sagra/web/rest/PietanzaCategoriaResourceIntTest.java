package it.alecata.sagra.web.rest;

import it.alecata.sagra.SagrabeApp;

import it.alecata.sagra.domain.PietanzaCategoria;
import it.alecata.sagra.repository.PietanzaCategoriaRepository;
import it.alecata.sagra.service.PietanzaCategoriaService;
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
 * Test class for the PietanzaCategoriaResource REST controller.
 *
 * @see PietanzaCategoriaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SagrabeApp.class)
public class PietanzaCategoriaResourceIntTest {

    private static final String DEFAULT_CODICE = "AAAAAAAAAA";
    private static final String UPDATED_CODICE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIZIONE = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIZIONE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIZIONE_BREVE = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIZIONE_BREVE = "BBBBBBBBBB";

    private static final String DEFAULT_NOME_STAMPANTE = "AAAAAAAAAA";
    private static final String UPDATED_NOME_STAMPANTE = "BBBBBBBBBB";

    @Autowired
    private PietanzaCategoriaRepository pietanzaCategoriaRepository;

    @Autowired
    private PietanzaCategoriaService pietanzaCategoriaService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPietanzaCategoriaMockMvc;

    private PietanzaCategoria pietanzaCategoria;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PietanzaCategoriaResource pietanzaCategoriaResource = new PietanzaCategoriaResource(pietanzaCategoriaService);
        this.restPietanzaCategoriaMockMvc = MockMvcBuilders.standaloneSetup(pietanzaCategoriaResource)
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
    public static PietanzaCategoria createEntity(EntityManager em) {
        PietanzaCategoria pietanzaCategoria = new PietanzaCategoria()
            .codice(DEFAULT_CODICE)
            .descrizione(DEFAULT_DESCRIZIONE)
            .descrizioneBreve(DEFAULT_DESCRIZIONE_BREVE)
            .nomeStampante(DEFAULT_NOME_STAMPANTE);
        return pietanzaCategoria;
    }

    @Before
    public void initTest() {
        pietanzaCategoria = createEntity(em);
    }

    @Test
    @Transactional
    public void createPietanzaCategoria() throws Exception {
        int databaseSizeBeforeCreate = pietanzaCategoriaRepository.findAll().size();

        // Create the PietanzaCategoria
        restPietanzaCategoriaMockMvc.perform(post("/api/pietanza-categorias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pietanzaCategoria)))
            .andExpect(status().isCreated());

        // Validate the PietanzaCategoria in the database
        List<PietanzaCategoria> pietanzaCategoriaList = pietanzaCategoriaRepository.findAll();
        assertThat(pietanzaCategoriaList).hasSize(databaseSizeBeforeCreate + 1);
        PietanzaCategoria testPietanzaCategoria = pietanzaCategoriaList.get(pietanzaCategoriaList.size() - 1);
        assertThat(testPietanzaCategoria.getCodice()).isEqualTo(DEFAULT_CODICE);
        assertThat(testPietanzaCategoria.getDescrizione()).isEqualTo(DEFAULT_DESCRIZIONE);
        assertThat(testPietanzaCategoria.getDescrizioneBreve()).isEqualTo(DEFAULT_DESCRIZIONE_BREVE);
        assertThat(testPietanzaCategoria.getNomeStampante()).isEqualTo(DEFAULT_NOME_STAMPANTE);
    }

    @Test
    @Transactional
    public void createPietanzaCategoriaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pietanzaCategoriaRepository.findAll().size();

        // Create the PietanzaCategoria with an existing ID
        pietanzaCategoria.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPietanzaCategoriaMockMvc.perform(post("/api/pietanza-categorias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pietanzaCategoria)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<PietanzaCategoria> pietanzaCategoriaList = pietanzaCategoriaRepository.findAll();
        assertThat(pietanzaCategoriaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPietanzaCategorias() throws Exception {
        // Initialize the database
        pietanzaCategoriaRepository.saveAndFlush(pietanzaCategoria);

        // Get all the pietanzaCategoriaList
        restPietanzaCategoriaMockMvc.perform(get("/api/pietanza-categorias?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pietanzaCategoria.getId().intValue())))
            .andExpect(jsonPath("$.[*].codice").value(hasItem(DEFAULT_CODICE.toString())))
            .andExpect(jsonPath("$.[*].descrizione").value(hasItem(DEFAULT_DESCRIZIONE.toString())))
            .andExpect(jsonPath("$.[*].descrizioneBreve").value(hasItem(DEFAULT_DESCRIZIONE_BREVE.toString())))
            .andExpect(jsonPath("$.[*].nomeStampante").value(hasItem(DEFAULT_NOME_STAMPANTE.toString())));
    }

    @Test
    @Transactional
    public void getPietanzaCategoria() throws Exception {
        // Initialize the database
        pietanzaCategoriaRepository.saveAndFlush(pietanzaCategoria);

        // Get the pietanzaCategoria
        restPietanzaCategoriaMockMvc.perform(get("/api/pietanza-categorias/{id}", pietanzaCategoria.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pietanzaCategoria.getId().intValue()))
            .andExpect(jsonPath("$.codice").value(DEFAULT_CODICE.toString()))
            .andExpect(jsonPath("$.descrizione").value(DEFAULT_DESCRIZIONE.toString()))
            .andExpect(jsonPath("$.descrizioneBreve").value(DEFAULT_DESCRIZIONE_BREVE.toString()))
            .andExpect(jsonPath("$.nomeStampante").value(DEFAULT_NOME_STAMPANTE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPietanzaCategoria() throws Exception {
        // Get the pietanzaCategoria
        restPietanzaCategoriaMockMvc.perform(get("/api/pietanza-categorias/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePietanzaCategoria() throws Exception {
        // Initialize the database
        pietanzaCategoriaService.save(pietanzaCategoria);

        int databaseSizeBeforeUpdate = pietanzaCategoriaRepository.findAll().size();

        // Update the pietanzaCategoria
        PietanzaCategoria updatedPietanzaCategoria = pietanzaCategoriaRepository.findOne(pietanzaCategoria.getId());
        updatedPietanzaCategoria
            .codice(UPDATED_CODICE)
            .descrizione(UPDATED_DESCRIZIONE)
            .descrizioneBreve(UPDATED_DESCRIZIONE_BREVE)
            .nomeStampante(UPDATED_NOME_STAMPANTE);

        restPietanzaCategoriaMockMvc.perform(put("/api/pietanza-categorias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPietanzaCategoria)))
            .andExpect(status().isOk());

        // Validate the PietanzaCategoria in the database
        List<PietanzaCategoria> pietanzaCategoriaList = pietanzaCategoriaRepository.findAll();
        assertThat(pietanzaCategoriaList).hasSize(databaseSizeBeforeUpdate);
        PietanzaCategoria testPietanzaCategoria = pietanzaCategoriaList.get(pietanzaCategoriaList.size() - 1);
        assertThat(testPietanzaCategoria.getCodice()).isEqualTo(UPDATED_CODICE);
        assertThat(testPietanzaCategoria.getDescrizione()).isEqualTo(UPDATED_DESCRIZIONE);
        assertThat(testPietanzaCategoria.getDescrizioneBreve()).isEqualTo(UPDATED_DESCRIZIONE_BREVE);
        assertThat(testPietanzaCategoria.getNomeStampante()).isEqualTo(UPDATED_NOME_STAMPANTE);
    }

    @Test
    @Transactional
    public void updateNonExistingPietanzaCategoria() throws Exception {
        int databaseSizeBeforeUpdate = pietanzaCategoriaRepository.findAll().size();

        // Create the PietanzaCategoria

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPietanzaCategoriaMockMvc.perform(put("/api/pietanza-categorias")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pietanzaCategoria)))
            .andExpect(status().isCreated());

        // Validate the PietanzaCategoria in the database
        List<PietanzaCategoria> pietanzaCategoriaList = pietanzaCategoriaRepository.findAll();
        assertThat(pietanzaCategoriaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePietanzaCategoria() throws Exception {
        // Initialize the database
        pietanzaCategoriaService.save(pietanzaCategoria);

        int databaseSizeBeforeDelete = pietanzaCategoriaRepository.findAll().size();

        // Get the pietanzaCategoria
        restPietanzaCategoriaMockMvc.perform(delete("/api/pietanza-categorias/{id}", pietanzaCategoria.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PietanzaCategoria> pietanzaCategoriaList = pietanzaCategoriaRepository.findAll();
        assertThat(pietanzaCategoriaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PietanzaCategoria.class);
        PietanzaCategoria pietanzaCategoria1 = new PietanzaCategoria();
        pietanzaCategoria1.setId(1L);
        PietanzaCategoria pietanzaCategoria2 = new PietanzaCategoria();
        pietanzaCategoria2.setId(pietanzaCategoria1.getId());
        assertThat(pietanzaCategoria1).isEqualTo(pietanzaCategoria2);
        pietanzaCategoria2.setId(2L);
        assertThat(pietanzaCategoria1).isNotEqualTo(pietanzaCategoria2);
        pietanzaCategoria1.setId(null);
        assertThat(pietanzaCategoria1).isNotEqualTo(pietanzaCategoria2);
    }
}
