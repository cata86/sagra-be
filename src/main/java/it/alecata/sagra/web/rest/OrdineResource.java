package it.alecata.sagra.web.rest;

import com.codahale.metrics.annotation.Timed;
import it.alecata.sagra.domain.Ordine;
import it.alecata.sagra.service.OrdineService;
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
 * REST controller for managing Ordine.
 */
@RestController
@RequestMapping("/api")
public class OrdineResource {

    private final Logger log = LoggerFactory.getLogger(OrdineResource.class);

    private static final String ENTITY_NAME = "ordine";

    private final OrdineService ordineService;

    public OrdineResource(OrdineService ordineService) {
        this.ordineService = ordineService;
    }

    /**
     * POST  /ordines : Create a new ordine.
     *
     * @param ordine the ordine to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ordine, or with status 400 (Bad Request) if the ordine has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ordines")
    @Timed
    public ResponseEntity<Ordine> createOrdine(@Valid @RequestBody Ordine ordine) throws URISyntaxException {
        log.debug("REST request to save Ordine : {}", ordine);
        if (ordine.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new ordine cannot already have an ID")).body(null);
        }
        Ordine result = ordineService.save(ordine);
        return ResponseEntity.created(new URI("/api/ordines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ordines : Updates an existing ordine.
     *
     * @param ordine the ordine to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ordine,
     * or with status 400 (Bad Request) if the ordine is not valid,
     * or with status 500 (Internal Server Error) if the ordine couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ordines")
    @Timed
    public ResponseEntity<Ordine> updateOrdine(@Valid @RequestBody Ordine ordine) throws URISyntaxException {
        log.debug("REST request to update Ordine : {}", ordine);
        if (ordine.getId() == null) {
            return createOrdine(ordine);
        }
        Ordine result = ordineService.save(ordine);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ordine.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ordines : get all the ordines.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of ordines in body
     */
    @GetMapping("/ordines")
    @Timed
    public ResponseEntity<List<Ordine>> getAllOrdines(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Ordines");
        Page<Ordine> page = ordineService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ordines");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /ordines/:id : get the "id" ordine.
     *
     * @param id the id of the ordine to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ordine, or with status 404 (Not Found)
     */
    @GetMapping("/ordines/{id}")
    @Timed
    public ResponseEntity<Ordine> getOrdine(@PathVariable Long id) {
        log.debug("REST request to get Ordine : {}", id);
        Ordine ordine = ordineService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ordine));
    }

    /**
     * DELETE  /ordines/:id : delete the "id" ordine.
     *
     * @param id the id of the ordine to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ordines/{id}")
    @Timed
    public ResponseEntity<Void> deleteOrdine(@PathVariable Long id) {
        log.debug("REST request to delete Ordine : {}", id);
        ordineService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
