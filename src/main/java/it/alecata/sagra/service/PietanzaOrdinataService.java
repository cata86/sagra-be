package it.alecata.sagra.service;

import it.alecata.sagra.domain.PietanzaOrdinata;
import it.alecata.sagra.repository.PietanzaOrdinataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing PietanzaOrdinata.
 */
@Service
@Transactional
public class PietanzaOrdinataService {

    private final Logger log = LoggerFactory.getLogger(PietanzaOrdinataService.class);

    private final PietanzaOrdinataRepository pietanzaOrdinataRepository;

    public PietanzaOrdinataService(PietanzaOrdinataRepository pietanzaOrdinataRepository) {
        this.pietanzaOrdinataRepository = pietanzaOrdinataRepository;
    }

    /**
     * Save a pietanzaOrdinata.
     *
     * @param pietanzaOrdinata the entity to save
     * @return the persisted entity
     */
    public PietanzaOrdinata save(PietanzaOrdinata pietanzaOrdinata) {
        log.debug("Request to save PietanzaOrdinata : {}", pietanzaOrdinata);
        return pietanzaOrdinataRepository.save(pietanzaOrdinata);
    }

    /**
     *  Get all the pietanzaOrdinatas.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<PietanzaOrdinata> findAll(Pageable pageable) {
        log.debug("Request to get all PietanzaOrdinatas");
        return pietanzaOrdinataRepository.findAll(pageable);
    }

    /**
     *  Get one pietanzaOrdinata by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public PietanzaOrdinata findOne(Long id) {
        log.debug("Request to get PietanzaOrdinata : {}", id);
        return pietanzaOrdinataRepository.findOne(id);
    }

    /**
     *  Delete the  pietanzaOrdinata by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete PietanzaOrdinata : {}", id);
        pietanzaOrdinataRepository.delete(id);
    }
}
