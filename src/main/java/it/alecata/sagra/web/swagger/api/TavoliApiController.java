package it.alecata.sagra.web.swagger.api;


import io.swagger.annotations.*;
import it.alecata.sagra.domain.Serata;
import it.alecata.sagra.domain.TavoloAccomodato;
import it.alecata.sagra.domain.TavoloReale;
import it.alecata.sagra.domain.enumeration.TavoloStato;
import it.alecata.sagra.service.SerataService;
import it.alecata.sagra.service.TavoloAccomodatoService;
import it.alecata.sagra.service.TavoloRealeService;
import it.alecata.sagra.web.swagger.model.TavoloAccomodatoDto;
import it.alecata.sagra.web.swagger.model.TavoloAccomodatoDto.StatoEnum;
import it.alecata.sagra.web.swagger.model.TavoloRealeDto;

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



    public ResponseEntity<TavoloAccomodatoDto> apriTavoloAccomodato(@ApiParam(value = "idTavoloReale, codice, descrizione, numCoperti, accomodatoPersona" ,required=true )  @Valid @RequestBody TavoloAccomodatoDto body) {
    	//idTavoloReale, codice, descrizione, numCoperti, accomodatoPersona
    	//FIXME serata me lo passano
    	
    	Serata serata = serataService.findLastSerata();
    	
    	TavoloReale tavoloReale = tavoloRealeService.findOne(body.getIdTavoloReale());
    	List<TavoloAccomodato> tavoliAccomodati = tavoloReale.getTavoliAccomodati();
    	String nuovoCodice = tavoloAccomodatoService.findCodeTavoloLibero(tavoloReale.getCodice(),tavoliAccomodati);
    	
    	TavoloAccomodato accomodato = new TavoloAccomodato();
    	accomodato.setCodice(nuovoCodice);
    	accomodato.setNomeAsporto(body.getNomeAsporto());
    	accomodato.setDescrizione("Tavolo " +nuovoCodice);
    	accomodato.setNumCoperti(body.getNumCoperti());
    	accomodato.setAccomodatoPersona(body.getAccomodatoPersona());
    	accomodato.setAccomodatoOrario(ZonedDateTime.now(ZoneId.systemDefault()));
    	accomodato.setStato(TavoloStato.ACCOMODATO);
    	accomodato.setAsporto(body.getAsporto());
    	accomodato.setSerata(serata);
    	accomodato.setTavoloReale(tavoloReale);
    	tavoloAccomodatoService.save(accomodato);
    	
    	TavoloAccomodatoDto tavoloAccomodatoDto = tavoloAccomodatoToDto(accomodato);
    	
        return new ResponseEntity<TavoloAccomodatoDto>(tavoloAccomodatoDto,HttpStatus.OK);
    }

    public ResponseEntity<TavoloAccomodatoDto> chiudiTavoloAccomodato(@ApiParam(value = "idTavoloAccomodato, accomodatoPersona" ,required=true )  @Valid @RequestBody TavoloAccomodatoDto body) {
    	TavoloAccomodato accomodato = tavoloAccomodatoService.findOne(body.getId());
    	accomodato.setLiberatoOrario(ZonedDateTime.now(ZoneId.systemDefault()));
    	accomodato.setLiberatoPersona(body.getLiberatoPersona());
    	accomodato.setStato(TavoloStato.LIBERATO);
    	
    	tavoloAccomodatoService.save(accomodato);
    	
    	TavoloAccomodatoDto tavoloAccomodatoDto = tavoloAccomodatoToDto(accomodato);
    	
        return new ResponseEntity<TavoloAccomodatoDto>(tavoloAccomodatoDto,HttpStatus.OK);
    }

    public ResponseEntity<List<TavoloAccomodatoDto>> listaTavoliAccomodatiByTavoloId( @NotNull@ApiParam(value = "Identificativo del tavolo reale", required = true) @RequestParam(value = "idTavoloReale", required = true) Long idTavoloReale) {
    	TavoloReale tavoloReale = tavoloRealeService.findOne(idTavoloReale);
    	List<TavoloAccomodato> tavoliAccomodati = tavoloReale.getTavoliAccomodati();
    	List<TavoloAccomodatoDto> response = new ArrayList<TavoloAccomodatoDto>();
    	for (TavoloAccomodato tavoloAccomodato : tavoliAccomodati) {
    		TavoloAccomodatoDto tavoloAccomodatoDto = tavoloAccomodatoToDto(tavoloAccomodato);
        	response.add(tavoloAccomodatoDto);      	
    	}
    	
        return new ResponseEntity<List<TavoloAccomodatoDto>>(response,HttpStatus.OK);
    }

    public ResponseEntity<List<TavoloRealeDto>> listaTavoliReali(@ApiParam(value = "Solo tavoli liberi") @RequestParam(value = "soloLiberi", required = false) Boolean soloLiberi) {
        // do some magic!
    	//FIXME parametro soloLiberi
    	
    	Page<TavoloReale> tavoliRealiPage = tavoloRealeService.findAll(new PageRequest(0, Integer.MAX_VALUE));
    	List<TavoloReale> tavoliReali = tavoliRealiPage.getContent();
    	List<TavoloRealeDto> response = new ArrayList<TavoloRealeDto>();
    	for (TavoloReale tavoloReale : tavoliReali) {
    		TavoloRealeDto tavoloDto = new TavoloRealeDto();
    		tavoloDto.setAsporto(tavoloReale.isAsporto());
    		tavoloDto.setCodice(tavoloReale.getCodice());
    		tavoloDto.setDescrizione(tavoloReale.getDescrizione());
    		tavoloDto.setId(tavoloReale.getId());
    		tavoloDto.setIdSagra(tavoloReale.getSagra().getId());
    		tavoloDto.setPostiMax(tavoloReale.getPostiMax());
    		tavoloDto.setPostiOccupati(tavoloReale.getPostiOccupati());
    		response.add(tavoloDto);
    	}
    	
        return new ResponseEntity<List<TavoloRealeDto>>(response,HttpStatus.OK);
    }

	public ResponseEntity<TavoloAccomodatoDto> impostaStatoTavoloAccomodato(Long idTavoloAccomodato, StatoEnum stato, String persona) {
    	TavoloAccomodato accomodato = tavoloAccomodatoService.findOne(idTavoloAccomodato);
    	accomodato.setLiberatoOrario(ZonedDateTime.now(ZoneId.systemDefault()));
    	if(stato.equals(StatoEnum.ACCOMODATO)){
    		accomodato.setAccomodatoPersona(persona);
    		accomodato.setAccomodatoOrario(ZonedDateTime.now(ZoneId.systemDefault()));
    	}else if (stato.equals(StatoEnum.IN_ORDINAZIONE)){
    		accomodato.setInOrdinazionePersona(persona);
    		accomodato.setInOrdinazioneOrario(ZonedDateTime.now(ZoneId.systemDefault()));
    	}else if (stato.equals(StatoEnum.ORDINATO)){
    		accomodato.setOrdinazionePersona(persona);
    		accomodato.setOrdinazioneOrario(ZonedDateTime.now(ZoneId.systemDefault()));
		}else if (stato.equals(StatoEnum.LIBERATO)){
    		accomodato.setLiberatoPersona(persona);
    		accomodato.setLiberatoOrario(ZonedDateTime.now(ZoneId.systemDefault()));
		}   	
    	accomodato.setStato(TavoloStato.valueOf(stato.name()));
    	tavoloAccomodatoService.save(accomodato);
    	
		return new ResponseEntity<TavoloAccomodatoDto>(tavoloAccomodatoToDto(accomodato),HttpStatus.OK);
	}
	
	private TavoloAccomodatoDto tavoloAccomodatoToDto(TavoloAccomodato tavoloAccomodato){
		TavoloAccomodatoDto tavoloAccomodatoDto = new TavoloAccomodatoDto();
		tavoloAccomodatoDto.setId(tavoloAccomodato.getId());
		tavoloAccomodatoDto.setIdSerata(tavoloAccomodato.getSerata().getId());
		tavoloAccomodatoDto.setCodice(tavoloAccomodato.getCodice());
		tavoloAccomodatoDto.setNomeAsporto(tavoloAccomodato.getNomeAsporto());
		tavoloAccomodatoDto.setNumCoperti(tavoloAccomodato.getNumCoperti());
		tavoloAccomodatoDto.setAsporto(tavoloAccomodato.isAsporto());
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
    	
    	return tavoloAccomodatoDto;
	}



}
