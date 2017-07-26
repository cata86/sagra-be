package it.alecata.sagra.web.rest;

import com.codahale.metrics.annotation.Timed;
import it.alecata.sagra.domain.Sagra;
import it.alecata.sagra.service.SagraService;
import it.alecata.sagra.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Sagra.
 */
@RestController
@RequestMapping("/api")
public class SagraResource {

    private final Logger log = LoggerFactory.getLogger(SagraResource.class);

    private static final String ENTITY_NAME = "sagra";

    private final SagraService sagraService;

    public SagraResource(SagraService sagraService) {
        this.sagraService = sagraService;
    }

    /**
     * POST  /sagras : Create a new sagra.
     *
     * @param sagra the sagra to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sagra, or with status 400 (Bad Request) if the sagra has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sagras")
    @Timed
    public ResponseEntity<Sagra> createSagra(@RequestBody Sagra sagra) throws URISyntaxException {
        log.debug("REST request to save Sagra : {}", sagra);
        if (sagra.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new sagra cannot already have an ID")).body(null);
        }
        Sagra result = sagraService.save(sagra);
        return ResponseEntity.created(new URI("/api/sagras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sagras : Updates an existing sagra.
     *
     * @param sagra the sagra to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sagra,
     * or with status 400 (Bad Request) if the sagra is not valid,
     * or with status 500 (Internal Server Error) if the sagra couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sagras")
    @Timed
    public ResponseEntity<Sagra> updateSagra(@RequestBody Sagra sagra) throws URISyntaxException {
        log.debug("REST request to update Sagra : {}", sagra);
        if (sagra.getId() == null) {
            return createSagra(sagra);
        }
        Sagra result = sagraService.save(sagra);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sagra.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sagras : get all the sagras.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sagras in body
     */
    @GetMapping("/sagras")
    @Timed
    public List<Sagra> getAllSagras() {
        log.debug("REST request to get all Sagras");
        return sagraService.findAll();
    }

    /**
     * GET  /sagras/:id : get the "id" sagra.
     *
     * @param id the id of the sagra to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sagra, or with status 404 (Not Found)
     */
    @GetMapping("/sagras/{id}")
    @Timed
    public ResponseEntity<Sagra> getSagra(@PathVariable Long id) {
        log.debug("REST request to get Sagra : {}", id);
        Sagra sagra = sagraService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(sagra));
    }

    /**
     * DELETE  /sagras/:id : delete the "id" sagra.
     *
     * @param id the id of the sagra to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sagras/{id}")
    @Timed
    public ResponseEntity<Void> deleteSagra(@PathVariable Long id) {
        log.debug("REST request to delete Sagra : {}", id);
        sagraService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
