package it.alecata.sagra.web.rest;

import com.codahale.metrics.annotation.Timed;
import it.alecata.sagra.domain.TavoloAccomodato;
import it.alecata.sagra.service.TavoloAccomodatoService;
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
 * REST controller for managing TavoloAccomodato.
 */
@RestController
@RequestMapping("/api")
public class TavoloAccomodatoResource {

    private final Logger log = LoggerFactory.getLogger(TavoloAccomodatoResource.class);

    private static final String ENTITY_NAME = "tavoloAccomodato";

    private final TavoloAccomodatoService tavoloAccomodatoService;

    public TavoloAccomodatoResource(TavoloAccomodatoService tavoloAccomodatoService) {
        this.tavoloAccomodatoService = tavoloAccomodatoService;
    }

    /**
     * POST  /tavolo-accomodatoes : Create a new tavoloAccomodato.
     *
     * @param tavoloAccomodato the tavoloAccomodato to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tavoloAccomodato, or with status 400 (Bad Request) if the tavoloAccomodato has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tavolo-accomodatoes")
    @Timed
    public ResponseEntity<TavoloAccomodato> createTavoloAccomodato(@Valid @RequestBody TavoloAccomodato tavoloAccomodato) throws URISyntaxException {
        log.debug("REST request to save TavoloAccomodato : {}", tavoloAccomodato);
        if (tavoloAccomodato.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new tavoloAccomodato cannot already have an ID")).body(null);
        }
        TavoloAccomodato result = tavoloAccomodatoService.save(tavoloAccomodato);
        return ResponseEntity.created(new URI("/api/tavolo-accomodatoes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tavolo-accomodatoes : Updates an existing tavoloAccomodato.
     *
     * @param tavoloAccomodato the tavoloAccomodato to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tavoloAccomodato,
     * or with status 400 (Bad Request) if the tavoloAccomodato is not valid,
     * or with status 500 (Internal Server Error) if the tavoloAccomodato couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tavolo-accomodatoes")
    @Timed
    public ResponseEntity<TavoloAccomodato> updateTavoloAccomodato(@Valid @RequestBody TavoloAccomodato tavoloAccomodato) throws URISyntaxException {
        log.debug("REST request to update TavoloAccomodato : {}", tavoloAccomodato);
        if (tavoloAccomodato.getId() == null) {
            return createTavoloAccomodato(tavoloAccomodato);
        }
        TavoloAccomodato result = tavoloAccomodatoService.save(tavoloAccomodato);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tavoloAccomodato.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tavolo-accomodatoes : get all the tavoloAccomodatoes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tavoloAccomodatoes in body
     */
    @GetMapping("/tavolo-accomodatoes")
    @Timed
    public ResponseEntity<List<TavoloAccomodato>> getAllTavoloAccomodatoes(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of TavoloAccomodatoes");
        Page<TavoloAccomodato> page = tavoloAccomodatoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tavolo-accomodatoes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /tavolo-accomodatoes/:id : get the "id" tavoloAccomodato.
     *
     * @param id the id of the tavoloAccomodato to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tavoloAccomodato, or with status 404 (Not Found)
     */
    @GetMapping("/tavolo-accomodatoes/{id}")
    @Timed
    public ResponseEntity<TavoloAccomodato> getTavoloAccomodato(@PathVariable Long id) {
        log.debug("REST request to get TavoloAccomodato : {}", id);
        TavoloAccomodato tavoloAccomodato = tavoloAccomodatoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tavoloAccomodato));
    }

    /**
     * DELETE  /tavolo-accomodatoes/:id : delete the "id" tavoloAccomodato.
     *
     * @param id the id of the tavoloAccomodato to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tavolo-accomodatoes/{id}")
    @Timed
    public ResponseEntity<Void> deleteTavoloAccomodato(@PathVariable Long id) {
        log.debug("REST request to delete TavoloAccomodato : {}", id);
        tavoloAccomodatoService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
