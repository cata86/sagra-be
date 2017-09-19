package it.alecata.sagra.service;

import it.alecata.sagra.domain.Serata;
import it.alecata.sagra.domain.TavoloAccomodato;
import it.alecata.sagra.domain.TavoloReale;
import it.alecata.sagra.domain.enumeration.TavoloStato;
import it.alecata.sagra.repository.TavoloAccomodatoRepository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing TavoloAccomodato.
 */
@Service
@Transactional
public class TavoloAccomodatoService {

    private final Logger log = LoggerFactory.getLogger(TavoloAccomodatoService.class);

    private final TavoloAccomodatoRepository tavoloAccomodatoRepository;

    public TavoloAccomodatoService(TavoloAccomodatoRepository tavoloAccomodatoRepository) {
        this.tavoloAccomodatoRepository = tavoloAccomodatoRepository;
    }

    /**
     * Save a tavoloAccomodato.
     *
     * @param tavoloAccomodato the entity to save
     * @return the persisted entity
     */
    public TavoloAccomodato save(TavoloAccomodato tavoloAccomodato) {
        log.debug("Request to save TavoloAccomodato : {}", tavoloAccomodato);
        return tavoloAccomodatoRepository.save(tavoloAccomodato);
    }

    /**
     *  Get all the tavoloAccomodatoes.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<TavoloAccomodato> findAll(Pageable pageable) {
        log.debug("Request to get all TavoloAccomodatoes");
        return tavoloAccomodatoRepository.findAll(pageable);
    }
    
    @Transactional(readOnly = true)
    public List<TavoloAccomodato> findAllBySerta(Serata serata) {
        log.debug("Request to get all TavoloAccomodatoes");
        return tavoloAccomodatoRepository.findAllBySerata(serata);
    }
    
    @Transactional(readOnly = true)
    public List<TavoloAccomodato> findAllBySerataAndTavoloReale(Serata serata, TavoloReale tavoloReale) {
        log.debug("Request to get all TavoloAccomodatoes");
        return tavoloAccomodatoRepository.findAllBySerataAndTavoloReale(serata, tavoloReale);
    }

    /**
     *  Get one tavoloAccomodato by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true)
    public TavoloAccomodato findOne(Long id) {
        log.debug("Request to get TavoloAccomodato : {}", id);
        return tavoloAccomodatoRepository.findOne(id);
    }

    /**
     *  Delete the  tavoloAccomodato by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete TavoloAccomodato : {}", id);
        tavoloAccomodatoRepository.delete(id);
    }
    
    public String findCodeTavoloLibero(String tavoloRealeId,List<TavoloAccomodato> tavoliAccomodati,Serata serata){
    	
    	for (int i = -1; i < 27*27; ++i) {
	    	boolean trovato = false;
	    	for(TavoloAccomodato accomodato : tavoliAccomodati){
	    		//!liberato & trovato
	    		if(accomodato.getSerata().getId().equals(serata.getId())){
		    		if((!accomodato.getStato().equals(TavoloStato.LIBERATO)) && accomodato.getCodice().equals(tavoloRealeId+str(i)) ){
		    			trovato = true;
		    		}
	    		}
	    	}
	    	if(!trovato)
	    		return tavoloRealeId+str(i);
    	}
    	return "000000000";
    }
    
    private String str(int i) {
        return i < 0 ? "" : str((i / 26) - 1) + (char)(65 + i % 26);
    }
}
