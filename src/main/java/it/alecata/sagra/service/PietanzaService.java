package it.alecata.sagra.service;

import it.alecata.sagra.domain.Pietanza;
import it.alecata.sagra.repository.PietanzaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Pietanza.
 */
@Service
@Transactional
public class PietanzaService {

    private final Logger log = LoggerFactory.getLogger(PietanzaService.class);

    private final PietanzaRepository pietanzaRepository;

    public PietanzaService(PietanzaRepository pietanzaRepository) {
        this.pietanzaRepository = pietanzaRepository;
    }

    /**
     * Save a pietanza.
     *
     * @param pietanza the entity to save
     * @return the persisted entity
     */
    public Pietanza save(Pietanza pietanza) {
        log.debug("Request to save Pietanza : {}", pietanza);
        return pietanzaRepository.save(pietanza);
    }

    /**
     *  Get all the pietanzas.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Pietanza> findAll(Pageable pageable) {
        log.debug("Request to get all Pietanzas");
        return pietanzaRepository.findAll(pageable);
    }

    /**
     *  Get one pietanza by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Pietanza findOne(Long id) {
        log.debug("Request to get Pietanza : {}", id);
        return pietanzaRepository.findOne(id);
    }

    /**
     *  Delete the  pietanza by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Pietanza : {}", id);
        pietanzaRepository.delete(id);
    }
}
