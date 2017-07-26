package it.alecata.sagra.web.rest;

import com.codahale.metrics.annotation.Timed;
import it.alecata.sagra.domain.TavoloReale;
import it.alecata.sagra.service.TavoloRealeService;
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
 * REST controller for managing TavoloReale.
 */
@RestController
@RequestMapping("/api")
public class TavoloRealeResource {

    private final Logger log = LoggerFactory.getLogger(TavoloRealeResource.class);

    private static final String ENTITY_NAME = "tavoloReale";

    private final TavoloRealeService tavoloRealeService;

    public TavoloRealeResource(TavoloRealeService tavoloRealeService) {
        this.tavoloRealeService = tavoloRealeService;
    }

    /**
     * POST  /tavolo-reales : Create a new tavoloReale.
     *
     * @param tavoloReale the tavoloReale to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tavoloReale, or with status 400 (Bad Request) if the tavoloReale has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tavolo-reales")
    @Timed
    public ResponseEntity<TavoloReale> createTavoloReale(@Valid @RequestBody TavoloReale tavoloReale) throws URISyntaxException {
        log.debug("REST request to save TavoloReale : {}", tavoloReale);
        if (tavoloReale.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new tavoloReale cannot already have an ID")).body(null);
        }
        TavoloReale result = tavoloRealeService.save(tavoloReale);
        return ResponseEntity.created(new URI("/api/tavolo-reales/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tavolo-reales : Updates an existing tavoloReale.
     *
     * @param tavoloReale the tavoloReale to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tavoloReale,
     * or with status 400 (Bad Request) if the tavoloReale is not valid,
     * or with status 500 (Internal Server Error) if the tavoloReale couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tavolo-reales")
    @Timed
    public ResponseEntity<TavoloReale> updateTavoloReale(@Valid @RequestBody TavoloReale tavoloReale) throws URISyntaxException {
        log.debug("REST request to update TavoloReale : {}", tavoloReale);
        if (tavoloReale.getId() == null) {
            return createTavoloReale(tavoloReale);
        }
        TavoloReale result = tavoloRealeService.save(tavoloReale);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tavoloReale.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tavolo-reales : get all the tavoloReales.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tavoloReales in body
     */
    @GetMapping("/tavolo-reales")
    @Timed
    public ResponseEntity<List<TavoloReale>> getAllTavoloReales(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of TavoloReales");
        Page<TavoloReale> page = tavoloRealeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tavolo-reales");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /tavolo-reales/:id : get the "id" tavoloReale.
     *
     * @param id the id of the tavoloReale to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tavoloReale, or with status 404 (Not Found)
     */
    @GetMapping("/tavolo-reales/{id}")
    @Timed
    public ResponseEntity<TavoloReale> getTavoloReale(@PathVariable Long id) {
        log.debug("REST request to get TavoloReale : {}", id);
        TavoloReale tavoloReale = tavoloRealeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tavoloReale));
    }

    /**
     * DELETE  /tavolo-reales/:id : delete the "id" tavoloReale.
     *
     * @param id the id of the tavoloReale to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tavolo-reales/{id}")
    @Timed
    public ResponseEntity<Void> deleteTavoloReale(@PathVariable Long id) {
        log.debug("REST request to delete TavoloReale : {}", id);
        tavoloRealeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
