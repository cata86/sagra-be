package it.alecata.sagra.web.rest;

import it.alecata.sagra.SagrabeApp;

import it.alecata.sagra.domain.Sagra;
import it.alecata.sagra.repository.SagraRepository;
import it.alecata.sagra.service.SagraService;
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
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SagraResource REST controller.
 *
 * @see SagraResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SagrabeApp.class)
public class SagraResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final byte[] DEFAULT_LOGO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_LOGO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_LOGO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_LOGO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_INDIRIZZO = "AAAAAAAAAA";
    private static final String UPDATED_INDIRIZZO = "BBBBBBBBBB";

    private static final String DEFAULT_PIVA = "AAAAAAAAAA";
    private static final String UPDATED_PIVA = "BBBBBBBBBB";

    private static final String DEFAULT_TESTATA_SCONTRINO = "AAAAAAAAAA";
    private static final String UPDATED_TESTATA_SCONTRINO = "BBBBBBBBBB";

    private static final String DEFAULT_FOOTER_SCONTRINO = "AAAAAAAAAA";
    private static final String UPDATED_FOOTER_SCONTRINO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SEQUENZE_ABILITATE = false;
    private static final Boolean UPDATED_SEQUENZE_ABILITATE = true;

    @Autowired
    private SagraRepository sagraRepository;

    @Autowired
    private SagraService sagraService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSagraMockMvc;

    private Sagra sagra;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SagraResource sagraResource = new SagraResource(sagraService);
        this.restSagraMockMvc = MockMvcBuilders.standaloneSetup(sagraResource)
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
    public static Sagra createEntity(EntityManager em) {
        Sagra sagra = new Sagra()
            .nome(DEFAULT_NOME)
            .logo(DEFAULT_LOGO)
            .logoContentType(DEFAULT_LOGO_CONTENT_TYPE)
            .indirizzo(DEFAULT_INDIRIZZO)
            .piva(DEFAULT_PIVA)
            .testataScontrino(DEFAULT_TESTATA_SCONTRINO)
            .footerScontrino(DEFAULT_FOOTER_SCONTRINO)
            .sequenzeAbilitate(DEFAULT_SEQUENZE_ABILITATE);
        return sagra;
    }

    @Before
    public void initTest() {
        sagra = createEntity(em);
    }

    @Test
    @Transactional
    public void createSagra() throws Exception {
        int databaseSizeBeforeCreate = sagraRepository.findAll().size();

        // Create the Sagra
        restSagraMockMvc.perform(post("/api/sagras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sagra)))
            .andExpect(status().isCreated());

        // Validate the Sagra in the database
        List<Sagra> sagraList = sagraRepository.findAll();
        assertThat(sagraList).hasSize(databaseSizeBeforeCreate + 1);
        Sagra testSagra = sagraList.get(sagraList.size() - 1);
        assertThat(testSagra.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testSagra.getLogo()).isEqualTo(DEFAULT_LOGO);
        assertThat(testSagra.getLogoContentType()).isEqualTo(DEFAULT_LOGO_CONTENT_TYPE);
        assertThat(testSagra.getIndirizzo()).isEqualTo(DEFAULT_INDIRIZZO);
        assertThat(testSagra.getPiva()).isEqualTo(DEFAULT_PIVA);
        assertThat(testSagra.getTestataScontrino()).isEqualTo(DEFAULT_TESTATA_SCONTRINO);
        assertThat(testSagra.getFooterScontrino()).isEqualTo(DEFAULT_FOOTER_SCONTRINO);
        assertThat(testSagra.isSequenzeAbilitate()).isEqualTo(DEFAULT_SEQUENZE_ABILITATE);
    }

    @Test
    @Transactional
    public void createSagraWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sagraRepository.findAll().size();

        // Create the Sagra with an existing ID
        sagra.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSagraMockMvc.perform(post("/api/sagras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sagra)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Sagra> sagraList = sagraRepository.findAll();
        assertThat(sagraList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSagras() throws Exception {
        // Initialize the database
        sagraRepository.saveAndFlush(sagra);

        // Get all the sagraList
        restSagraMockMvc.perform(get("/api/sagras?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sagra.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].logoContentType").value(hasItem(DEFAULT_LOGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].logo").value(hasItem(Base64Utils.encodeToString(DEFAULT_LOGO))))
            .andExpect(jsonPath("$.[*].indirizzo").value(hasItem(DEFAULT_INDIRIZZO.toString())))
            .andExpect(jsonPath("$.[*].piva").value(hasItem(DEFAULT_PIVA.toString())))
            .andExpect(jsonPath("$.[*].testataScontrino").value(hasItem(DEFAULT_TESTATA_SCONTRINO.toString())))
            .andExpect(jsonPath("$.[*].footerScontrino").value(hasItem(DEFAULT_FOOTER_SCONTRINO.toString())))
            .andExpect(jsonPath("$.[*].sequenzeAbilitate").value(hasItem(DEFAULT_SEQUENZE_ABILITATE.booleanValue())));
    }

    @Test
    @Transactional
    public void getSagra() throws Exception {
        // Initialize the database
        sagraRepository.saveAndFlush(sagra);

        // Get the sagra
        restSagraMockMvc.perform(get("/api/sagras/{id}", sagra.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sagra.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.logoContentType").value(DEFAULT_LOGO_CONTENT_TYPE))
            .andExpect(jsonPath("$.logo").value(Base64Utils.encodeToString(DEFAULT_LOGO)))
            .andExpect(jsonPath("$.indirizzo").value(DEFAULT_INDIRIZZO.toString()))
            .andExpect(jsonPath("$.piva").value(DEFAULT_PIVA.toString()))
            .andExpect(jsonPath("$.testataScontrino").value(DEFAULT_TESTATA_SCONTRINO.toString()))
            .andExpect(jsonPath("$.footerScontrino").value(DEFAULT_FOOTER_SCONTRINO.toString()))
            .andExpect(jsonPath("$.sequenzeAbilitate").value(DEFAULT_SEQUENZE_ABILITATE.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSagra() throws Exception {
        // Get the sagra
        restSagraMockMvc.perform(get("/api/sagras/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSagra() throws Exception {
        // Initialize the database
        sagraService.save(sagra);

        int databaseSizeBeforeUpdate = sagraRepository.findAll().size();

        // Update the sagra
        Sagra updatedSagra = sagraRepository.findOne(sagra.getId());
        updatedSagra
            .nome(UPDATED_NOME)
            .logo(UPDATED_LOGO)
            .logoContentType(UPDATED_LOGO_CONTENT_TYPE)
            .indirizzo(UPDATED_INDIRIZZO)
            .piva(UPDATED_PIVA)
            .testataScontrino(UPDATED_TESTATA_SCONTRINO)
            .footerScontrino(UPDATED_FOOTER_SCONTRINO)
            .sequenzeAbilitate(UPDATED_SEQUENZE_ABILITATE);

        restSagraMockMvc.perform(put("/api/sagras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSagra)))
            .andExpect(status().isOk());

        // Validate the Sagra in the database
        List<Sagra> sagraList = sagraRepository.findAll();
        assertThat(sagraList).hasSize(databaseSizeBeforeUpdate);
        Sagra testSagra = sagraList.get(sagraList.size() - 1);
        assertThat(testSagra.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testSagra.getLogo()).isEqualTo(UPDATED_LOGO);
        assertThat(testSagra.getLogoContentType()).isEqualTo(UPDATED_LOGO_CONTENT_TYPE);
        assertThat(testSagra.getIndirizzo()).isEqualTo(UPDATED_INDIRIZZO);
        assertThat(testSagra.getPiva()).isEqualTo(UPDATED_PIVA);
        assertThat(testSagra.getTestataScontrino()).isEqualTo(UPDATED_TESTATA_SCONTRINO);
        assertThat(testSagra.getFooterScontrino()).isEqualTo(UPDATED_FOOTER_SCONTRINO);
        assertThat(testSagra.isSequenzeAbilitate()).isEqualTo(UPDATED_SEQUENZE_ABILITATE);
    }

    @Test
    @Transactional
    public void updateNonExistingSagra() throws Exception {
        int databaseSizeBeforeUpdate = sagraRepository.findAll().size();

        // Create the Sagra

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSagraMockMvc.perform(put("/api/sagras")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sagra)))
            .andExpect(status().isCreated());

        // Validate the Sagra in the database
        List<Sagra> sagraList = sagraRepository.findAll();
        assertThat(sagraList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSagra() throws Exception {
        // Initialize the database
        sagraService.save(sagra);

        int databaseSizeBeforeDelete = sagraRepository.findAll().size();

        // Get the sagra
        restSagraMockMvc.perform(delete("/api/sagras/{id}", sagra.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Sagra> sagraList = sagraRepository.findAll();
        assertThat(sagraList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sagra.class);
        Sagra sagra1 = new Sagra();
        sagra1.setId(1L);
        Sagra sagra2 = new Sagra();
        sagra2.setId(sagra1.getId());
        assertThat(sagra1).isEqualTo(sagra2);
        sagra2.setId(2L);
        assertThat(sagra1).isNotEqualTo(sagra2);
        sagra1.setId(null);
        assertThat(sagra1).isNotEqualTo(sagra2);
    }
}
