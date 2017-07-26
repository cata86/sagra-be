package it.alecata.sagra.web.rest;

import com.codahale.metrics.annotation.Timed;
import it.alecata.sagra.domain.Serata;
import it.alecata.sagra.service.SerataService;
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
 * REST controller for managing Serata.
 */
@RestController
@RequestMapping("/api")
public class SerataResource {

    private final Logger log = LoggerFactory.getLogger(SerataResource.class);

    private static final String ENTITY_NAME = "serata";

    private final SerataService serataService;

    public SerataResource(SerataService serataService) {
        this.serataService = serataService;
    }

    /**
     * POST  /seratas : Create a new serata.
     *
     * @param serata the serata to create
     * @return the ResponseEntity with status 201 (Created) and with body the new serata, or with status 400 (Bad Request) if the serata has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/seratas")
    @Timed
    public ResponseEntity<Serata> createSerata(@Valid @RequestBody Serata serata) throws URISyntaxException {
        log.debug("REST request to save Serata : {}", serata);
        if (serata.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new serata cannot already have an ID")).body(null);
        }
        Serata result = serataService.save(serata);
        return ResponseEntity.created(new URI("/api/seratas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /seratas : Updates an existing serata.
     *
     * @param serata the serata to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated serata,
     * or with status 400 (Bad Request) if the serata is not valid,
     * or with status 500 (Internal Server Error) if the serata couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/seratas")
    @Timed
    public ResponseEntity<Serata> updateSerata(@Valid @RequestBody Serata serata) throws URISyntaxException {
        log.debug("REST request to update Serata : {}", serata);
        if (serata.getId() == null) {
            return createSerata(serata);
        }
        Serata result = serataService.save(serata);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, serata.getId().toString()))
            .body(result);
    }

    /**
     * GET  /seratas : get all the seratas.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of seratas in body
     */
    @GetMapping("/seratas")
    @Timed
    public ResponseEntity<List<Serata>> getAllSeratas(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Seratas");
        Page<Serata> page = serataService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/seratas");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /seratas/:id : get the "id" serata.
     *
     * @param id the id of the serata to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the serata, or with status 404 (Not Found)
     */
    @GetMapping("/seratas/{id}")
    @Timed
    public ResponseEntity<Serata> getSerata(@PathVariable Long id) {
        log.debug("REST request to get Serata : {}", id);
        Serata serata = serataService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(serata));
    }

    /**
     * DELETE  /seratas/:id : delete the "id" serata.
     *
     * @param id the id of the serata to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/seratas/{id}")
    @Timed
    public ResponseEntity<Void> deleteSerata(@PathVariable Long id) {
        log.debug("REST request to delete Serata : {}", id);
        serataService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
