package it.alecata.sagra.web.rest;

import it.alecata.sagra.SagrabeApp;

import it.alecata.sagra.domain.TavoloAccomodato;
import it.alecata.sagra.domain.Serata;
import it.alecata.sagra.domain.TavoloReale;
import it.alecata.sagra.repository.TavoloAccomodatoRepository;
import it.alecata.sagra.service.TavoloAccomodatoService;
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

import it.alecata.sagra.domain.enumeration.TavoloStato;
/**
 * Test class for the TavoloAccomodatoResource REST controller.
 *
 * @see TavoloAccomodatoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SagrabeApp.class)
public class TavoloAccomodatoResourceIntTest {

    private static final String DEFAULT_CODICE = "AAAAAAAAAA";
    private static final String UPDATED_CODICE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIZIONE = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIZIONE = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUM_COPERTI = 1;
    private static final Integer UPDATED_NUM_COPERTI = 2;

    private static final TavoloStato DEFAULT_STATO = TavoloStato.ACCOMODATO;
    private static final TavoloStato UPDATED_STATO = TavoloStato.IN_ORDINAZIONE;

    private static final ZonedDateTime DEFAULT_ACCOMODATO_ORARIO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ACCOMODATO_ORARIO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_ACCOMODATO_PERSONA = "AAAAAAAAAA";
    private static final String UPDATED_ACCOMODATO_PERSONA = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_IN_ORDINAZIONE_ORARIO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_IN_ORDINAZIONE_ORARIO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_IN_ORDINAZIONE_PERSONA = "AAAAAAAAAA";
    private static final String UPDATED_IN_ORDINAZIONE_PERSONA = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_ORDINAZIONE_ORARIO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_ORDINAZIONE_ORARIO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_ORDINAZIONE_PERSONA = "AAAAAAAAAA";
    private static final String UPDATED_ORDINAZIONE_PERSONA = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_LIBERATO_ORARIO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LIBERATO_ORARIO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_LIBERATO_PERSONA = "AAAAAAAAAA";
    private static final String UPDATED_LIBERATO_PERSONA = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ASPORTO = false;
    private static final Boolean UPDATED_ASPORTO = true;

    @Autowired
    private TavoloAccomodatoRepository tavoloAccomodatoRepository;

    @Autowired
    private TavoloAccomodatoService tavoloAccomodatoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTavoloAccomodatoMockMvc;

    private TavoloAccomodato tavoloAccomodato;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TavoloAccomodatoResource tavoloAccomodatoResource = new TavoloAccomodatoResource(tavoloAccomodatoService);
        this.restTavoloAccomodatoMockMvc = MockMvcBuilders.standaloneSetup(tavoloAccomodatoResource)
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
    public static TavoloAccomodato createEntity(EntityManager em) {
        TavoloAccomodato tavoloAccomodato = new TavoloAccomodato()
            .codice(DEFAULT_CODICE)
            .descrizione(DEFAULT_DESCRIZIONE)
            .numCoperti(DEFAULT_NUM_COPERTI)
            .stato(DEFAULT_STATO)
            .accomodatoOrario(DEFAULT_ACCOMODATO_ORARIO)
            .accomodatoPersona(DEFAULT_ACCOMODATO_PERSONA)
            .inOrdinazioneOrario(DEFAULT_IN_ORDINAZIONE_ORARIO)
            .inOrdinazionePersona(DEFAULT_IN_ORDINAZIONE_PERSONA)
            .ordinazioneOrario(DEFAULT_ORDINAZIONE_ORARIO)
            .ordinazionePersona(DEFAULT_ORDINAZIONE_PERSONA)
            .liberatoOrario(DEFAULT_LIBERATO_ORARIO)
            .liberatoPersona(DEFAULT_LIBERATO_PERSONA)
            .asporto(DEFAULT_ASPORTO);
        // Add required entity
        Serata serata = SerataResourceIntTest.createEntity(em);
        em.persist(serata);
        em.flush();
        tavoloAccomodato.setSerata(serata);
        // Add required entity
        TavoloReale tavoloReale = TavoloRealeResourceIntTest.createEntity(em);
        em.persist(tavoloReale);
        em.flush();
        tavoloAccomodato.setTavoloReale(tavoloReale);
        return tavoloAccomodato;
    }

    @Before
    public void initTest() {
        tavoloAccomodato = createEntity(em);
    }

    @Test
    @Transactional
    public void createTavoloAccomodato() throws Exception {
        int databaseSizeBeforeCreate = tavoloAccomodatoRepository.findAll().size();

        // Create the TavoloAccomodato
        restTavoloAccomodatoMockMvc.perform(post("/api/tavolo-accomodatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tavoloAccomodato)))
            .andExpect(status().isCreated());

        // Validate the TavoloAccomodato in the database
        List<TavoloAccomodato> tavoloAccomodatoList = tavoloAccomodatoRepository.findAll();
        assertThat(tavoloAccomodatoList).hasSize(databaseSizeBeforeCreate + 1);
        TavoloAccomodato testTavoloAccomodato = tavoloAccomodatoList.get(tavoloAccomodatoList.size() - 1);
        assertThat(testTavoloAccomodato.getCodice()).isEqualTo(DEFAULT_CODICE);
        assertThat(testTavoloAccomodato.getDescrizione()).isEqualTo(DEFAULT_DESCRIZIONE);
        assertThat(testTavoloAccomodato.getNumCoperti()).isEqualTo(DEFAULT_NUM_COPERTI);
        assertThat(testTavoloAccomodato.getStato()).isEqualTo(DEFAULT_STATO);
        assertThat(testTavoloAccomodato.getAccomodatoOrario()).isEqualTo(DEFAULT_ACCOMODATO_ORARIO);
        assertThat(testTavoloAccomodato.getAccomodatoPersona()).isEqualTo(DEFAULT_ACCOMODATO_PERSONA);
        assertThat(testTavoloAccomodato.getInOrdinazioneOrario()).isEqualTo(DEFAULT_IN_ORDINAZIONE_ORARIO);
        assertThat(testTavoloAccomodato.getInOrdinazionePersona()).isEqualTo(DEFAULT_IN_ORDINAZIONE_PERSONA);
        assertThat(testTavoloAccomodato.getOrdinazioneOrario()).isEqualTo(DEFAULT_ORDINAZIONE_ORARIO);
        assertThat(testTavoloAccomodato.getOrdinazionePersona()).isEqualTo(DEFAULT_ORDINAZIONE_PERSONA);
        assertThat(testTavoloAccomodato.getLiberatoOrario()).isEqualTo(DEFAULT_LIBERATO_ORARIO);
        assertThat(testTavoloAccomodato.getLiberatoPersona()).isEqualTo(DEFAULT_LIBERATO_PERSONA);
        assertThat(testTavoloAccomodato.isAsporto()).isEqualTo(DEFAULT_ASPORTO);
    }

    @Test
    @Transactional
    public void createTavoloAccomodatoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tavoloAccomodatoRepository.findAll().size();

        // Create the TavoloAccomodato with an existing ID
        tavoloAccomodato.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTavoloAccomodatoMockMvc.perform(post("/api/tavolo-accomodatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tavoloAccomodato)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<TavoloAccomodato> tavoloAccomodatoList = tavoloAccomodatoRepository.findAll();
        assertThat(tavoloAccomodatoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTavoloAccomodatoes() throws Exception {
        // Initialize the database
        tavoloAccomodatoRepository.saveAndFlush(tavoloAccomodato);

        // Get all the tavoloAccomodatoList
        restTavoloAccomodatoMockMvc.perform(get("/api/tavolo-accomodatoes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tavoloAccomodato.getId().intValue())))
            .andExpect(jsonPath("$.[*].codice").value(hasItem(DEFAULT_CODICE.toString())))
            .andExpect(jsonPath("$.[*].descrizione").value(hasItem(DEFAULT_DESCRIZIONE.toString())))
            .andExpect(jsonPath("$.[*].numCoperti").value(hasItem(DEFAULT_NUM_COPERTI)))
            .andExpect(jsonPath("$.[*].stato").value(hasItem(DEFAULT_STATO.toString())))
            .andExpect(jsonPath("$.[*].accomodatoOrario").value(hasItem(sameInstant(DEFAULT_ACCOMODATO_ORARIO))))
            .andExpect(jsonPath("$.[*].accomodatoPersona").value(hasItem(DEFAULT_ACCOMODATO_PERSONA.toString())))
            .andExpect(jsonPath("$.[*].inOrdinazioneOrario").value(hasItem(sameInstant(DEFAULT_IN_ORDINAZIONE_ORARIO))))
            .andExpect(jsonPath("$.[*].inOrdinazionePersona").value(hasItem(DEFAULT_IN_ORDINAZIONE_PERSONA.toString())))
            .andExpect(jsonPath("$.[*].ordinazioneOrario").value(hasItem(sameInstant(DEFAULT_ORDINAZIONE_ORARIO))))
            .andExpect(jsonPath("$.[*].ordinazionePersona").value(hasItem(DEFAULT_ORDINAZIONE_PERSONA.toString())))
            .andExpect(jsonPath("$.[*].liberatoOrario").value(hasItem(sameInstant(DEFAULT_LIBERATO_ORARIO))))
            .andExpect(jsonPath("$.[*].liberatoPersona").value(hasItem(DEFAULT_LIBERATO_PERSONA.toString())))
            .andExpect(jsonPath("$.[*].asporto").value(hasItem(DEFAULT_ASPORTO.booleanValue())));
    }

    @Test
    @Transactional
    public void getTavoloAccomodato() throws Exception {
        // Initialize the database
        tavoloAccomodatoRepository.saveAndFlush(tavoloAccomodato);

        // Get the tavoloAccomodato
        restTavoloAccomodatoMockMvc.perform(get("/api/tavolo-accomodatoes/{id}", tavoloAccomodato.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tavoloAccomodato.getId().intValue()))
            .andExpect(jsonPath("$.codice").value(DEFAULT_CODICE.toString()))
            .andExpect(jsonPath("$.descrizione").value(DEFAULT_DESCRIZIONE.toString()))
            .andExpect(jsonPath("$.numCoperti").value(DEFAULT_NUM_COPERTI))
            .andExpect(jsonPath("$.stato").value(DEFAULT_STATO.toString()))
            .andExpect(jsonPath("$.accomodatoOrario").value(sameInstant(DEFAULT_ACCOMODATO_ORARIO)))
            .andExpect(jsonPath("$.accomodatoPersona").value(DEFAULT_ACCOMODATO_PERSONA.toString()))
            .andExpect(jsonPath("$.inOrdinazioneOrario").value(sameInstant(DEFAULT_IN_ORDINAZIONE_ORARIO)))
            .andExpect(jsonPath("$.inOrdinazionePersona").value(DEFAULT_IN_ORDINAZIONE_PERSONA.toString()))
            .andExpect(jsonPath("$.ordinazioneOrario").value(sameInstant(DEFAULT_ORDINAZIONE_ORARIO)))
            .andExpect(jsonPath("$.ordinazionePersona").value(DEFAULT_ORDINAZIONE_PERSONA.toString()))
            .andExpect(jsonPath("$.liberatoOrario").value(sameInstant(DEFAULT_LIBERATO_ORARIO)))
            .andExpect(jsonPath("$.liberatoPersona").value(DEFAULT_LIBERATO_PERSONA.toString()))
            .andExpect(jsonPath("$.asporto").value(DEFAULT_ASPORTO.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTavoloAccomodato() throws Exception {
        // Get the tavoloAccomodato
        restTavoloAccomodatoMockMvc.perform(get("/api/tavolo-accomodatoes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTavoloAccomodato() throws Exception {
        // Initialize the database
        tavoloAccomodatoService.save(tavoloAccomodato);

        int databaseSizeBeforeUpdate = tavoloAccomodatoRepository.findAll().size();

        // Update the tavoloAccomodato
        TavoloAccomodato updatedTavoloAccomodato = tavoloAccomodatoRepository.findOne(tavoloAccomodato.getId());
        updatedTavoloAccomodato
            .codice(UPDATED_CODICE)
            .descrizione(UPDATED_DESCRIZIONE)
            .numCoperti(UPDATED_NUM_COPERTI)
            .stato(UPDATED_STATO)
            .accomodatoOrario(UPDATED_ACCOMODATO_ORARIO)
            .accomodatoPersona(UPDATED_ACCOMODATO_PERSONA)
            .inOrdinazioneOrario(UPDATED_IN_ORDINAZIONE_ORARIO)
            .inOrdinazionePersona(UPDATED_IN_ORDINAZIONE_PERSONA)
            .ordinazioneOrario(UPDATED_ORDINAZIONE_ORARIO)
            .ordinazionePersona(UPDATED_ORDINAZIONE_PERSONA)
            .liberatoOrario(UPDATED_LIBERATO_ORARIO)
            .liberatoPersona(UPDATED_LIBERATO_PERSONA)
            .asporto(UPDATED_ASPORTO);

        restTavoloAccomodatoMockMvc.perform(put("/api/tavolo-accomodatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTavoloAccomodato)))
            .andExpect(status().isOk());

        // Validate the TavoloAccomodato in the database
        List<TavoloAccomodato> tavoloAccomodatoList = tavoloAccomodatoRepository.findAll();
        assertThat(tavoloAccomodatoList).hasSize(databaseSizeBeforeUpdate);
        TavoloAccomodato testTavoloAccomodato = tavoloAccomodatoList.get(tavoloAccomodatoList.size() - 1);
        assertThat(testTavoloAccomodato.getCodice()).isEqualTo(UPDATED_CODICE);
        assertThat(testTavoloAccomodato.getDescrizione()).isEqualTo(UPDATED_DESCRIZIONE);
        assertThat(testTavoloAccomodato.getNumCoperti()).isEqualTo(UPDATED_NUM_COPERTI);
        assertThat(testTavoloAccomodato.getStato()).isEqualTo(UPDATED_STATO);
        assertThat(testTavoloAccomodato.getAccomodatoOrario()).isEqualTo(UPDATED_ACCOMODATO_ORARIO);
        assertThat(testTavoloAccomodato.getAccomodatoPersona()).isEqualTo(UPDATED_ACCOMODATO_PERSONA);
        assertThat(testTavoloAccomodato.getInOrdinazioneOrario()).isEqualTo(UPDATED_IN_ORDINAZIONE_ORARIO);
        assertThat(testTavoloAccomodato.getInOrdinazionePersona()).isEqualTo(UPDATED_IN_ORDINAZIONE_PERSONA);
        assertThat(testTavoloAccomodato.getOrdinazioneOrario()).isEqualTo(UPDATED_ORDINAZIONE_ORARIO);
        assertThat(testTavoloAccomodato.getOrdinazionePersona()).isEqualTo(UPDATED_ORDINAZIONE_PERSONA);
        assertThat(testTavoloAccomodato.getLiberatoOrario()).isEqualTo(UPDATED_LIBERATO_ORARIO);
        assertThat(testTavoloAccomodato.getLiberatoPersona()).isEqualTo(UPDATED_LIBERATO_PERSONA);
        assertThat(testTavoloAccomodato.isAsporto()).isEqualTo(UPDATED_ASPORTO);
    }

    @Test
    @Transactional
    public void updateNonExistingTavoloAccomodato() throws Exception {
        int databaseSizeBeforeUpdate = tavoloAccomodatoRepository.findAll().size();

        // Create the TavoloAccomodato

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTavoloAccomodatoMockMvc.perform(put("/api/tavolo-accomodatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tavoloAccomodato)))
            .andExpect(status().isCreated());

        // Validate the TavoloAccomodato in the database
        List<TavoloAccomodato> tavoloAccomodatoList = tavoloAccomodatoRepository.findAll();
        assertThat(tavoloAccomodatoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTavoloAccomodato() throws Exception {
        // Initialize the database
        tavoloAccomodatoService.save(tavoloAccomodato);

        int databaseSizeBeforeDelete = tavoloAccomodatoRepository.findAll().size();

        // Get the tavoloAccomodato
        restTavoloAccomodatoMockMvc.perform(delete("/api/tavolo-accomodatoes/{id}", tavoloAccomodato.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TavoloAccomodato> tavoloAccomodatoList = tavoloAccomodatoRepository.findAll();
        assertThat(tavoloAccomodatoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TavoloAccomodato.class);
        TavoloAccomodato tavoloAccomodato1 = new TavoloAccomodato();
        tavoloAccomodato1.setId(1L);
        TavoloAccomodato tavoloAccomodato2 = new TavoloAccomodato();
        tavoloAccomodato2.setId(tavoloAccomodato1.getId());
        assertThat(tavoloAccomodato1).isEqualTo(tavoloAccomodato2);
        tavoloAccomodato2.setId(2L);
        assertThat(tavoloAccomodato1).isNotEqualTo(tavoloAccomodato2);
        tavoloAccomodato1.setId(null);
        assertThat(tavoloAccomodato1).isNotEqualTo(tavoloAccomodato2);
    }
}
