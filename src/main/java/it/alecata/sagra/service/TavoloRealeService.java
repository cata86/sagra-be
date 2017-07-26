package it.alecata.sagra.service;

import it.alecata.sagra.domain.TavoloReale;
import it.alecata.sagra.repository.TavoloRealeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing TavoloReale.
 */
@Service
@Transactional
public class TavoloRealeService {

    private final Logger log = LoggerFactory.getLogger(TavoloRealeService.class);

    private final TavoloRealeRepository tavoloRealeRepository;

    public TavoloRealeService(TavoloRealeRepository tavoloRealeRepository) {
        this.tavoloRealeRepository = tavoloRealeRepository;
    }

    /**
     * Save a tavoloReale.
     *
     * @param tavoloReale the entity to save
     * @return the persisted entity
     */
    public TavoloReale save(TavoloReale tavoloReale) {
        log.debug("Request to save TavoloReale : {}", tavoloReale);
        return tavoloRealeRepository.save(tavoloReale);
    }

    /**
     *  Get all the tavoloReales.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<TavoloReale> findAll(Pageable pageable) {
        log.debug("Request to get all TavoloReales");
        return tavoloRealeRepository.findAll(pageable);
    }

    /**
     *  Get one tavoloReale by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public TavoloReale findOne(Long id) {
        log.debug("Request to get TavoloReale : {}", id);
        return tavoloRealeRepository.findOne(id);
    }

    /**
     *  Delete the  tavoloReale by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete TavoloReale : {}", id);
        tavoloRealeRepository.delete(id);
    }
}
