package it.alecata.sagra.service;

import it.alecata.sagra.domain.Serata;
import it.alecata.sagra.repository.SerataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Serata.
 */
@Service
@Transactional
public class SerataService {

    private final Logger log = LoggerFactory.getLogger(SerataService.class);

    private final SerataRepository serataRepository;

    public SerataService(SerataRepository serataRepository) {
        this.serataRepository = serataRepository;
    }

    /**
     * Save a serata.
     *
     * @param serata the entity to save
     * @return the persisted entity
     */
    public Serata save(Serata serata) {
        log.debug("Request to save Serata : {}", serata);
        return serataRepository.save(serata);
    }

    /**
     *  Get all the seratas.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Serata> findAll(Pageable pageable) {
        log.debug("Request to get all Seratas");
        return serataRepository.findAll(pageable);
    }

    /**
     *  Get one serata by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public Serata findOne(Long id) {
        log.debug("Request to get Serata : {}", id);
        return serataRepository.findOne(id);
    }

    /**
     *  Delete the  serata by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Serata : {}", id);
        serataRepository.delete(id);
    }
    
    @Transactional(readOnly = true)
    public Serata findLastSerata() {
        log.debug("Request to findLastSerata");
        return serataRepository.findByDataChiusuraIsNull();
    }
}
