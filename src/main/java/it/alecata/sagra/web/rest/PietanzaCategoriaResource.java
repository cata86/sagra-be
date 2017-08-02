package it.alecata.sagra.web.rest;

import com.codahale.metrics.annotation.Timed;
import it.alecata.sagra.domain.PietanzaCategoria;
import it.alecata.sagra.service.PietanzaCategoriaService;
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
 * REST controller for managing PietanzaCategoria.
 */
@RestController
@RequestMapping("/api")
public class PietanzaCategoriaResource {

    private final Logger log = LoggerFactory.getLogger(PietanzaCategoriaResource.class);

    private static final String ENTITY_NAME = "pietanzaCategoria";

    private final PietanzaCategoriaService pietanzaCategoriaService;

    public PietanzaCategoriaResource(PietanzaCategoriaService pietanzaCategoriaService) {
        this.pietanzaCategoriaService = pietanzaCategoriaService;
    }

    /**
     * POST  /pietanza-categorias : Create a new pietanzaCategoria.
     *
     * @param pietanzaCategoria the pietanzaCategoria to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pietanzaCategoria, or with status 400 (Bad Request) if the pietanzaCategoria has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pietanza-categorias")
    @Timed
    public ResponseEntity<PietanzaCategoria> createPietanzaCategoria(@RequestBody PietanzaCategoria pietanzaCategoria) throws URISyntaxException {
        log.debug("REST request to save PietanzaCategoria : {}", pietanzaCategoria);
        if (pietanzaCategoria.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new pietanzaCategoria cannot already have an ID")).body(null);
        }
        PietanzaCategoria result = pietanzaCategoriaService.save(pietanzaCategoria);
        return ResponseEntity.created(new URI("/api/pietanza-categorias/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pietanza-categorias : Updates an existing pietanzaCategoria.
     *
     * @param pietanzaCategoria the pietanzaCategoria to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pietanzaCategoria,
     * or with status 400 (Bad Request) if the pietanzaCategoria is not valid,
     * or with status 500 (Internal Server Error) if the pietanzaCategoria couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pietanza-categorias")
    @Timed
    public ResponseEntity<PietanzaCategoria> updatePietanzaCategoria(@RequestBody PietanzaCategoria pietanzaCategoria) throws URISyntaxException {
        log.debug("REST request to update PietanzaCategoria : {}", pietanzaCategoria);
        if (pietanzaCategoria.getId() == null) {
            return createPietanzaCategoria(pietanzaCategoria);
        }
        PietanzaCategoria result = pietanzaCategoriaService.save(pietanzaCategoria);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pietanzaCategoria.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pietanza-categorias : get all the pietanzaCategorias.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of pietanzaCategorias in body
     */
    @GetMapping("/pietanza-categorias")
    @Timed
    public List<PietanzaCategoria> getAllPietanzaCategorias() {
        log.debug("REST request to get all PietanzaCategorias");
        return pietanzaCategoriaService.findAll();
    }

    /**
     * GET  /pietanza-categorias/:id : get the "id" pietanzaCategoria.
     *
     * @param id the id of the pietanzaCategoria to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pietanzaCategoria, or with status 404 (Not Found)
     */
    @GetMapping("/pietanza-categorias/{id}")
    @Timed
    public ResponseEntity<PietanzaCategoria> getPietanzaCategoria(@PathVariable Long id) {
        log.debug("REST request to get PietanzaCategoria : {}", id);
        PietanzaCategoria pietanzaCategoria = pietanzaCategoriaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(pietanzaCategoria));
    }

    /**
     * DELETE  /pietanza-categorias/:id : delete the "id" pietanzaCategoria.
     *
     * @param id the id of the pietanzaCategoria to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pietanza-categorias/{id}")
    @Timed
    public ResponseEntity<Void> deletePietanzaCategoria(@PathVariable Long id) {
        log.debug("REST request to delete PietanzaCategoria : {}", id);
        pietanzaCategoriaService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
