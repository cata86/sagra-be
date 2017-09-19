package it.alecata.sagra.web.swagger.api;


import io.swagger.annotations.*;
import it.alecata.sagra.domain.Ordine;
import it.alecata.sagra.domain.Pietanza;
import it.alecata.sagra.domain.PietanzaCategoria;
import it.alecata.sagra.domain.PietanzaOrdinata;
import it.alecata.sagra.domain.Serata;
import it.alecata.sagra.domain.TavoloAccomodato;
import it.alecata.sagra.domain.enumeration.TavoloStato;
import it.alecata.sagra.repository.OrdineRepository;
import it.alecata.sagra.repository.PietanzaCategoriaRepository;
import it.alecata.sagra.repository.PietanzaOrdinataRepository;
import it.alecata.sagra.repository.PietanzaRepository;
import it.alecata.sagra.service.OrdineService;
import it.alecata.sagra.service.SerataService;
import it.alecata.sagra.service.TavoloAccomodatoService;
import it.alecata.sagra.service.TavoloRealeService;
import it.alecata.sagra.web.swagger.model.OrdineDto;
import it.alecata.sagra.web.swagger.model.PietanzaCategoriaDto;
import it.alecata.sagra.web.swagger.model.PietanzaDto;
import it.alecata.sagra.web.swagger.model.PietanzaOrdinataDto;
import it.alecata.sagra.web.swagger.model.TavoloAccomodatoDto;
import it.alecata.sagra.web.swagger.model.TavoloAccomodatoDto.StatoEnum;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-28T13:04:29.118Z")

@Controller
public class OrdiniApiController implements OrdiniApi {

    private final TavoloAccomodatoService tavoloAccomodatoService;

    private final SerataService serataService;
    
    private final TavoloRealeService tavoloRealeService;
    
    private final PietanzaCategoriaRepository pietanzaCategoriaRepository;
    
    private final PietanzaRepository pietanzaRepository;
    
    private final OrdineRepository ordineRepository;
    
    private final PietanzaOrdinataRepository pietanzaOrdinataRepository;
    
    private final OrdineService ordineService;

    public OrdiniApiController(TavoloAccomodatoService tavoloAccomodatoService, SerataService serataService, TavoloRealeService tavoloRealeService,
    		PietanzaCategoriaRepository pietanzaCategoriaRepository, PietanzaRepository pietanzaRepository, OrdineRepository ordineRepository,
    		PietanzaOrdinataRepository pietanzaOrdinataRepository, OrdineService ordineService) {
        this.tavoloAccomodatoService = tavoloAccomodatoService;
        this.serataService = serataService;
        this.tavoloRealeService = tavoloRealeService;
        this.pietanzaCategoriaRepository = pietanzaCategoriaRepository;
        this.pietanzaRepository = pietanzaRepository;
        this.ordineRepository = ordineRepository;
        this.pietanzaOrdinataRepository = pietanzaOrdinataRepository;
        this.ordineService = ordineService;
    }


    public ResponseEntity<OrdineDto> creaOrdine(@ApiParam(value = "ordine" ,required=true )  @Valid @RequestBody OrdineDto body) {
    	
    	body = ordineService.creaOrdine(body);
        return new ResponseEntity<OrdineDto>(body,HttpStatus.OK);
    }

    public ResponseEntity<List<OrdineDto>> listaOrdiniByTavoloId( @NotNull@ApiParam(value = "Identificativo del tavolo accomodato", required = true) @RequestParam(value = "idTavoloAccomodato", required = true) Long idTavoloAccomodato) {
    	List<OrdineDto> ordini = ordineService.listaOrdiniByTavoloId(idTavoloAccomodato);
    	
        return new ResponseEntity<List<OrdineDto>>(ordini,HttpStatus.OK);
    }

    public ResponseEntity<List<PietanzaDto>> listaPietanze( @NotNull@ApiParam(value = "Identificativo della sagra", required = true) @RequestParam(value = "idSagra", required = true) Long idSagra) {
    	List<PietanzaDto> response = ordineService.listaPietanze(idSagra);
        return new ResponseEntity<List<PietanzaDto>>(response,HttpStatus.OK);
    }
    
    public ResponseEntity<List<PietanzaCategoriaDto>> listaCategoriePietanze( @NotNull@ApiParam(value = "Identificativo della sagra", required = true) @RequestParam(value = "idSagra", required = true) Long idSagra) {
    	
    	Page<PietanzaCategoria> pietanzaCategoriaPage = pietanzaCategoriaRepository.findAll(new PageRequest(0, Integer.MAX_VALUE));
    	List<PietanzaCategoria> pietanzaCategorie = pietanzaCategoriaPage.getContent();
    	List<PietanzaCategoriaDto> response = new ArrayList<PietanzaCategoriaDto>();
    	for (PietanzaCategoria pietanzaCategoria : pietanzaCategorie) {
    		PietanzaCategoriaDto pietanzaCategoriaDto = new PietanzaCategoriaDto();
    		pietanzaCategoriaDto.setId(pietanzaCategoria.getId());
    		pietanzaCategoriaDto.setCodice(pietanzaCategoria.getCodice());
    		pietanzaCategoriaDto.setDescrizione(pietanzaCategoria.getDescrizione());
    		pietanzaCategoriaDto.setDescrizioneBreve(pietanzaCategoria.getDescrizioneBreve());
    		pietanzaCategoriaDto.setNomeStampante(pietanzaCategoria.getNomeStampante());
    		response.add(pietanzaCategoriaDto);
    	}
        return new ResponseEntity<List<PietanzaCategoriaDto>>(response,HttpStatus.OK);
    }

    public ResponseEntity<List<TavoloAccomodatoDto>> listaTavoliAccomodati( @NotNull@ApiParam(value = "Anche i tavoli in stato ordinato", required = true) @RequestParam(value = "statoOrdinato", required = true) Boolean statoOrdinato,
    		@NotNull@ApiParam(value = "Anche i tavolo di asporto", required = true) @RequestParam(value = "asporto", required = true) Boolean asporto) {
        // do some magic!
    	//FIXME parametro statoOrdinato
    	Serata serata = serataService.findLastSerata();
    	List<TavoloAccomodato> tavoliAccomodati = tavoloAccomodatoService.findAllBySerta(serata);
    	//List<TavoloAccomodato> tavoliAccomodati = tavoliAccomodatiPage.getContent();
    	List<TavoloAccomodatoDto> response = new ArrayList<TavoloAccomodatoDto>();
    	for (TavoloAccomodato tavoloAccomodato : tavoliAccomodati) {
    		if(tavoloAccomodato.getSerata().getId().equals(serata.getId())){
	    		if(!tavoloAccomodato.getStato().equals(TavoloStato.LIBERATO)){
	    			if(asporto || (!tavoloAccomodato.isAsporto())){
			    		TavoloAccomodatoDto tavoloAccomodatoDto = new TavoloAccomodatoDto();
			    		tavoloAccomodatoDto.setId(tavoloAccomodato.getId());
			    		tavoloAccomodatoDto.setIdSerata(tavoloAccomodato.getSerata().getId());
			    		tavoloAccomodatoDto.setCodice(tavoloAccomodato.getCodice());
			    		tavoloAccomodatoDto.setDescrizione(tavoloAccomodato.getDescrizione());
			    		tavoloAccomodatoDto.setAccomodatoOrario(new DateTime(tavoloAccomodato.getAccomodatoOrario().toInstant().toEpochMilli(), DateTimeZone.getDefault()));
			    		tavoloAccomodatoDto.setAccomodatoPersona(tavoloAccomodato.getAccomodatoPersona());
			    		if(tavoloAccomodato.getLiberatoOrario()!=null)
			    			tavoloAccomodatoDto.setLiberatoOrario(new DateTime(tavoloAccomodato.getLiberatoOrario().toInstant().toEpochMilli(), DateTimeZone.getDefault()));
			    		tavoloAccomodatoDto.setLiberatoPersona(tavoloAccomodato.getLiberatoPersona());
			        	if(tavoloAccomodato.getOrdinazioneOrario()!=null)
			        		tavoloAccomodatoDto.setOrdinazioneOrario(new DateTime(tavoloAccomodato.getOrdinazioneOrario().toInstant().toEpochMilli(), DateTimeZone.getDefault()));
			        	tavoloAccomodatoDto.setOrdinazionePersona(tavoloAccomodato.getOrdinazionePersona());
			        	if(tavoloAccomodato.getInOrdinazioneOrario()!=null)
			        		tavoloAccomodatoDto.setInOrdinazioneOrario(new DateTime(tavoloAccomodato.getInOrdinazioneOrario().toInstant().toEpochMilli(), DateTimeZone.getDefault()));
			        	tavoloAccomodatoDto.setInOrdinazionePersona(tavoloAccomodato.getInOrdinazionePersona());
			        	tavoloAccomodatoDto.setStato(StatoEnum.fromValue(tavoloAccomodato.getStato().toString()));
			        	tavoloAccomodatoDto.setNumCoperti(tavoloAccomodato.getNumCoperti());
			        	tavoloAccomodatoDto.setAsporto(tavoloAccomodato.isAsporto());
			        	tavoloAccomodatoDto.setNomeAsporto(tavoloAccomodato.getNomeAsporto());
			        	
			        	response.add(tavoloAccomodatoDto);
	    			}
	    		}
    		}
    	}
        return new ResponseEntity<List<TavoloAccomodatoDto>>(response,HttpStatus.OK);
    }


	@Override
	public ResponseEntity<Void> cancellaOrdine(@NotNull@ApiParam(value = "Identificativo della serata", required = true) @RequestParam(value = "idOrdine", required = true) Long idOrdine) {
		ordineService.cancellaOrdine(idOrdine);
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@Override
	public ResponseEntity<List<PietanzaOrdinataDto>> getContatori() {
		List<PietanzaOrdinataDto> pietanzeOrdinate =  ordineService.getContatori();
		return new ResponseEntity<>(pietanzeOrdinate,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<OrdineDto> modificaOrdine(@ApiParam(value = "ordine" ,required=true )  @Valid @RequestBody OrdineDto body) {
    	body = ordineService.modificaOrdine(body);
        return new ResponseEntity<OrdineDto>(body,HttpStatus.OK);
	}


	@Override
	public ResponseEntity<OrdineDto> stampaCucina(@NotNull@ApiParam(value = "Identificativo dell'ordine", required = true) @RequestParam(value = "idOrdine", required = true) Long idOrdine) {
		OrdineDto ordineDto = ordineService.stampaCucina(idOrdine);
		return new ResponseEntity<OrdineDto>(ordineDto,HttpStatus.OK);
	}


	@Override
	public ResponseEntity<OrdineDto> stampaScontrino(@NotNull@ApiParam(value = "Identificativo dell'ordine", required = true) @RequestParam(value = "idOrdine", required = true) Long idOrdine) {
		OrdineDto ordineDto = ordineService.stampaScontrino(idOrdine);
		return new ResponseEntity<OrdineDto>(ordineDto,HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<OrdineDto> stampaTutto(@NotNull@ApiParam(value = "Identificativo dell'ordine", required = true) @RequestParam(value = "idOrdine", required = true) Long idOrdine) {
		OrdineDto ordineDto = ordineService.stampaTutto(idOrdine);
		return new ResponseEntity<OrdineDto>(ordineDto,HttpStatus.OK);
	}


	@Override
	public ResponseEntity<List<PietanzaDto>> setContatori(@ApiParam(value = "pietanze con contatore" ,required=true )  @Valid @RequestBody List<PietanzaDto> body) {
		for(PietanzaDto pietanzaDto : body){
			Pietanza pietanza = pietanzaRepository.findOne(pietanzaDto.getId());
			pietanza.setContatore(pietanzaDto.getContatore());
			pietanzaRepository.save(pietanza);
		}
		
		return new ResponseEntity<List<PietanzaDto>>(body,HttpStatus.OK);
	}
	

}
