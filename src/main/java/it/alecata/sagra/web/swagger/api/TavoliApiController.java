package it.alecata.sagra.web.swagger.api;


import io.swagger.annotations.*;
import it.alecata.sagra.domain.TavoloAccomodato;
import it.alecata.sagra.domain.TavoloReale;
import it.alecata.sagra.domain.enumeration.TavoloStato;
import it.alecata.sagra.service.SerataService;
import it.alecata.sagra.service.TavoloAccomodatoService;
import it.alecata.sagra.service.TavoloRealeService;
import it.alecata.sagra.web.swagger.model.Body2;
import it.alecata.sagra.web.swagger.model.Body3;
import it.alecata.sagra.web.swagger.model.InlineResponse2001;
import it.alecata.sagra.web.swagger.model.InlineResponse2002;
import it.alecata.sagra.web.swagger.model.InlineResponse2003;
import it.alecata.sagra.web.swagger.model.TavoloAccomodato.StatoEnum;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-25T09:45:13.933Z")

@Controller
public class TavoliApiController implements TavoliApi {
	
    private final TavoloAccomodatoService tavoloAccomodatoService;

    private final SerataService serataService;
    
    private final TavoloRealeService tavoloRealeService;

    public TavoliApiController(TavoloAccomodatoService tavoloAccomodatoService, SerataService serataService, TavoloRealeService tavoloRealeService) {
        this.tavoloAccomodatoService = tavoloAccomodatoService;
        this.serataService = serataService;
        this.tavoloRealeService = tavoloRealeService;
    }



    public ResponseEntity<InlineResponse2003> apriTavoloAccomodato(@ApiParam(value = "idTavoloReale, codice, descrizione, numCoperti, accomodatoPersona" ,required=true )  @Valid @RequestBody Body2 body) {
    	//idTavoloReale, codice, descrizione, numCoperti, accomodatoPersona
    	it.alecata.sagra.domain.Serata serata = serataService.findLastSerata();
    	
    	TavoloReale tavoloReale = tavoloRealeService.findOne(body.getIdTavoloReale());
    	List<TavoloAccomodato> tavoliAccomodati = tavoloReale.getTavoliAccomodati();
    	String nuovoCodice = tavoloAccomodatoService.findCodeTavoloLibero(body.getIdTavoloReale(),tavoliAccomodati);
    	
    	TavoloAccomodato accomodato = new TavoloAccomodato();
    	accomodato.setCodice(nuovoCodice);
    	accomodato.setDescrizione("Tavolo " +nuovoCodice);
    	accomodato.setNumCoperti(body.getNumCoperti());
    	accomodato.setAccomodatoPersona(body.getAccomodatoPersona());
    	accomodato.setAccomodatoOrario(ZonedDateTime.now(ZoneId.systemDefault()));
    	accomodato.setStato(TavoloStato.ACCOMODATO);
    	accomodato.setAsporto(body.getAsporto());
    	accomodato.setSerata(serata);
    	accomodato.setTavoloReale(tavoloReale);
    	tavoloAccomodatoService.save(accomodato);
    	
    	InlineResponse2003 response = new InlineResponse2003();
    	response.setId(accomodato.getId());
    	response.setIdSerata(serata.getId());
    	response.setCodice(accomodato.getCodice());
    	response.setDescrizione(accomodato.getDescrizione());
    	response.setAccomodatoOrario(new DateTime(accomodato.getAccomodatoOrario().toInstant().toEpochMilli(), DateTimeZone.getDefault()));
    	response.setAccomodatoPersona(accomodato.getAccomodatoPersona());
    	response.setStato(StatoEnum.fromValue(accomodato.getStato().toString()));
    	
        return new ResponseEntity<InlineResponse2003>(response,HttpStatus.OK);
    }

    public ResponseEntity<InlineResponse2003> chiudiTavoloAccomodato(@ApiParam(value = "idTavoloAccomodato, accomodatoPersona" ,required=true )  @Valid @RequestBody Body3 body) {
    	TavoloAccomodato accomodato = tavoloAccomodatoService.findOne(body.getId());
    	accomodato.setLiberatoOrario(ZonedDateTime.now(ZoneId.systemDefault()));
    	accomodato.setLiberatoPersona(body.getLiberatoPersona());
    	accomodato.setStato(TavoloStato.LIBERATO);
    	
    	tavoloAccomodatoService.save(accomodato);
    	
    	InlineResponse2003 response = new InlineResponse2003();
    	response.setId(accomodato.getId());
    	response.setIdSerata(accomodato.getSerata().getId());
    	response.setCodice(accomodato.getCodice());
    	response.setDescrizione(accomodato.getDescrizione());
    	response.setAccomodatoOrario(new DateTime(accomodato.getAccomodatoOrario().toInstant().toEpochMilli(), DateTimeZone.getDefault()));
    	response.setAccomodatoPersona(accomodato.getAccomodatoPersona());
    	response.setLiberatoOrario(new DateTime(accomodato.getLiberatoOrario().toInstant().toEpochMilli(), DateTimeZone.getDefault()));
    	response.setLiberatoPersona(accomodato.getLiberatoPersona());
    	if(accomodato.getOrdinazioneOrario()!=null)
    		response.setOrdinazioneOrario(new DateTime(accomodato.getOrdinazioneOrario().toInstant().toEpochMilli(), DateTimeZone.getDefault()));
    	response.setOrdinazionePersona(accomodato.getOrdinazionePersona());
    	if(accomodato.getInOrdinazioneOrario()!=null)
    		response.setInOrdinazioneOrario(new DateTime(accomodato.getInOrdinazioneOrario().toInstant().toEpochMilli(), DateTimeZone.getDefault()));
    	response.setInOrdinazionePersona(accomodato.getInOrdinazionePersona());
    	response.setStato(StatoEnum.fromValue(accomodato.getStato().toString()));
    	
        return new ResponseEntity<InlineResponse2003>(response,HttpStatus.OK);
    }

    public ResponseEntity<List<InlineResponse2002>> listaTavoliAccomodatiByTavoloId( @NotNull@ApiParam(value = "Identificativo del tavolo reale", required = true) @RequestParam(value = "idTavoloReale", required = true) Long idTavoloReale) {
    	TavoloReale tavoloReale = tavoloRealeService.findOne(idTavoloReale);
    	List<TavoloAccomodato> tavoliAccomodati = tavoloReale.getTavoliAccomodati();
    	List<InlineResponse2002> response = new ArrayList<InlineResponse2002>();
    	for (TavoloAccomodato tavoloAccomodato : tavoliAccomodati) {
    		InlineResponse2002 tavoloAccomodatoDto = new InlineResponse2002();
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
        	response.add(tavoloAccomodatoDto);      	
    	}
    	
        return new ResponseEntity<List<InlineResponse2002>>(response,HttpStatus.OK);
    }

    public ResponseEntity<List<InlineResponse2001>> listaTavoliReali(@ApiParam(value = "Solo tavoli liberi") @RequestParam(value = "soloLiberi", required = false) Boolean soloLiberi) {
        // do some magic!
    	//FIXME parametro soloLiberi
    	
    	Page<TavoloReale> tavoliRealiPage = tavoloRealeService.findAll(new PageRequest(0, Integer.MAX_VALUE));
    	List<TavoloReale> tavoliReali = tavoliRealiPage.getContent();
    	List<InlineResponse2001> response = new ArrayList<InlineResponse2001>();
    	for (TavoloReale tavoloReale : tavoliReali) {
    		InlineResponse2001 tavoloDto = new InlineResponse2001();
    		tavoloDto.setAsporto(tavoloReale.isAsporto());
    		tavoloDto.setCodice(tavoloReale.getCodice());
    		tavoloDto.setDescrizione(tavoloReale.getDescrizione());
    		tavoloDto.setId(tavoloReale.getId());
    		tavoloDto.setIdSagra(tavoloReale.getSagra().getId());
    		tavoloDto.setPostiMax(tavoloReale.getPostiMax());
    		tavoloDto.setPostiOccupati(tavoloReale.getPostiOccupati());
    		response.add(tavoloDto);
    	}
    	
        return new ResponseEntity<List<InlineResponse2001>>(response,HttpStatus.OK);
    }

}
