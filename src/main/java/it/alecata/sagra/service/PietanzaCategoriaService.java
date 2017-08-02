package it.alecata.sagra.service;

import it.alecata.sagra.domain.PietanzaCategoria;
import it.alecata.sagra.repository.PietanzaCategoriaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Implementation for managing PietanzaCategoria.
 */
@Service
@Transactional
public class PietanzaCategoriaService {

    private final Logger log = LoggerFactory.getLogger(PietanzaCategoriaService.class);

    private final PietanzaCategoriaRepository pietanzaCategoriaRepository;

    public PietanzaCategoriaService(PietanzaCategoriaRepository pietanzaCategoriaRepository) {
        this.pietanzaCategoriaRepository = pietanzaCategoriaRepository;
    }

    /**
     * Save a pietanzaCategoria.
     *
     * @param pietanzaCategoria the entity to save
     * @return the persisted entity
     */
    public PietanzaCategoria save(PietanzaCategoria pietanzaCategoria) {
        log.debug("Request to save PietanzaCategoria : {}", pietanzaCategoria);
        return pietanzaCategoriaRepository.save(pietanzaCategoria);
    }

    /**
     *  Get all the pietanzaCategorias.
     *
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<PietanzaCategoria> findAll() {
        log.debug("Request to get all PietanzaCategorias");
        return pietanzaCategoriaRepository.findAll();
    }

    /**
     *  Get one pietanzaCategoria by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public PietanzaCategoria findOne(Long id) {
        log.debug("Request to get PietanzaCategoria : {}", id);
        return pietanzaCategoriaRepository.findOne(id);
    }

    /**
     *  Delete the  pietanzaCategoria by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete PietanzaCategoria : {}", id);
        pietanzaCategoriaRepository.delete(id);
    }
}
