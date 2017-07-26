package it.alecata.sagra.service;

import it.alecata.sagra.domain.Sagra;
import it.alecata.sagra.repository.SagraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing Sagra.
 */
@Service
@Transactional
public class SagraService {

    private final Logger log = LoggerFactory.getLogger(SagraService.class);

    private final SagraRepository sagraRepository;

    public SagraService(SagraRepository sagraRepository) {
        this.sagraRepository = sagraRepository;
    }

    /**
     * Save a sagra.
     *
     * @param sagra the entity to save
     * @return the persisted entity
     */
    public Sagra save(Sagra sagra) {
        log.debug("Request to save Sagra : {}", sagra);
        return sagraRepository.save(sagra);
    }

    /**
     *  Get all the sagras.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<Sagra> findAll() {
        log.debug("Request to get all Sagras");
        return sagraRepository.findAll();
    }

    /**
     *  Get one sagra by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Sagra findOne(Long id) {
        log.debug("Request to get Sagra : {}", id);
        return sagraRepository.findOne(id);
    }

    /**
     *  Delete the  sagra by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Sagra : {}", id);
        sagraRepository.delete(id);
    }
}
