package it.alecata.sagra.web.rest;

import com.codahale.metrics.annotation.Timed;
import it.alecata.sagra.domain.PietanzaOrdinata;
import it.alecata.sagra.service.PietanzaOrdinataService;
import it.alecata.sagra.web.rest.util.HeaderUtil;
import it.alecata.sagra.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PietanzaOrdinata.
 */
@RestController
@RequestMapping("/api")
public class PietanzaOrdinataResource {

    private final Logger log = LoggerFactory.getLogger(PietanzaOrdinataResource.class);

    private static final String ENTITY_NAME = "pietanzaOrdinata";

    private final PietanzaOrdinataService pietanzaOrdinataService;

    public PietanzaOrdinataResource(PietanzaOrdinataService pietanzaOrdinataService) {
        this.pietanzaOrdinataService = pietanzaOrdinataService;
    }

    /**
     * POST  /pietanza-ordinatas : Create a new pietanzaOrdinata.
     *
     * @param pietanzaOrdinata the pietanzaOrdinata to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pietanzaOrdinata, or with status 400 (Bad Request) if the pietanzaOrdinata has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pietanza-ordinatas")
    @Timed
    public ResponseEntity<PietanzaOrdinata> createPietanzaOrdinata(@Valid @RequestBody PietanzaOrdinata pietanzaOrdinata) throws URISyntaxException {
        log.debug("REST request to save PietanzaOrdinata : {}", pietanzaOrdinata);
        if (pietanzaOrdinata.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new pietanzaOrdinata cannot already have an ID")).body(null);
        }
        PietanzaOrdinata result = pietanzaOrdinataService.save(pietanzaOrdinata);
        return ResponseEntity.created(new URI("/api/pietanza-ordinatas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pietanza-ordinatas : Updates an existing pietanzaOrdinata.
     *
     * @param pietanzaOrdinata the pietanzaOrdinata to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pietanzaOrdinata,
     * or with status 400 (Bad Request) if the pietanzaOrdinata is not valid,
     * or with status 500 (Internal Server Error) if the pietanzaOrdinata couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pietanza-ordinatas")
    @Timed
    public ResponseEntity<PietanzaOrdinata> updatePietanzaOrdinata(@Valid @RequestBody PietanzaOrdinata pietanzaOrdinata) throws URISyntaxException {
        log.debug("REST request to update PietanzaOrdinata : {}", pietanzaOrdinata);
        if (pietanzaOrdinata.getId() == null) {
            return createPietanzaOrdinata(pietanzaOrdinata);
        }
        PietanzaOrdinata result = pietanzaOrdinataService.save(pietanzaOrdinata);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pietanzaOrdinata.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pietanza-ordinatas : get all the pietanzaOrdinatas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of pietanzaOrdinatas in body
     */
    @GetMapping("/pietanza-ordinatas")
    @Timed
    public ResponseEntity<List<PietanzaOrdinata>> getAllPietanzaOrdinatas(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of PietanzaOrdinatas");
        Page<PietanzaOrdinata> page = pietanzaOrdinataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pietanza-ordinatas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /pietanza-ordinatas/:id : get the "id" pietanzaOrdinata.
     *
     * @param id the id of the pietanzaOrdinata to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pietanzaOrdinata, or with status 404 (Not Found)
     */
    @GetMapping("/pietanza-ordinatas/{id}")
    @Timed
    public ResponseEntity<PietanzaOrdinata> getPietanzaOrdinata(@PathVariable Long id) {
        log.debug("REST request to get PietanzaOrdinata : {}", id);
        PietanzaOrdinata pietanzaOrdinata = pietanzaOrdinataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(pietanzaOrdinata));
    }

    /**
     * DELETE  /pietanza-ordinatas/:id : delete the "id" pietanzaOrdinata.
     *
     * @param id the id of the pietanzaOrdinata to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pietanza-ordinatas/{id}")
    @Timed
    public ResponseEntity<Void> deletePietanzaOrdinata(@PathVariable Long id) {
        log.debug("REST request to delete PietanzaOrdinata : {}", id);
        pietanzaOrdinataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
