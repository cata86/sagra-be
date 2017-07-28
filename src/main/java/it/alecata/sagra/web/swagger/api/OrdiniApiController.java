package it.alecata.sagra.web.swagger.api;


import io.swagger.annotations.*;
import it.alecata.sagra.domain.TavoloAccomodato;
import it.alecata.sagra.domain.TavoloReale;
import it.alecata.sagra.domain.enumeration.TavoloStato;
import it.alecata.sagra.service.SerataService;
import it.alecata.sagra.service.TavoloAccomodatoService;
import it.alecata.sagra.service.TavoloRealeService;
import it.alecata.sagra.web.swagger.model.OrdineDto;
import it.alecata.sagra.web.swagger.model.PietanzaDto;
import it.alecata.sagra.web.swagger.model.TavoloAccomodatoDto;
import it.alecata.sagra.web.swagger.model.TavoloRealeDto;
import it.alecata.sagra.web.swagger.model.TavoloAccomodatoDto.StatoEnum;

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

    public OrdiniApiController(TavoloAccomodatoService tavoloAccomodatoService, SerataService serataService, TavoloRealeService tavoloRealeService) {
        this.tavoloAccomodatoService = tavoloAccomodatoService;
        this.serataService = serataService;
        this.tavoloRealeService = tavoloRealeService;
    }

    public ResponseEntity<List<OrdineDto>> creaOrdine(@ApiParam(value = "ordine" ,required=true )  @Valid @RequestBody OrdineDto body) {
        // do some magic!
        return new ResponseEntity<List<OrdineDto>>(HttpStatus.OK);
    }

    public ResponseEntity<List<OrdineDto>> listaOrdiniByTavoloId( @NotNull@ApiParam(value = "Identificativo del tavolo accomodato", required = true) @RequestParam(value = "idTavoloAccomodato", required = true) Long idTavoloAccomodato) {
        // do some magic!
        return new ResponseEntity<List<OrdineDto>>(HttpStatus.OK);
    }

    public ResponseEntity<List<PietanzaDto>> listaPietanze( @NotNull@ApiParam(value = "Identificativo della sagra", required = true) @RequestParam(value = "idSagra", required = true) Boolean idSagra) {
        // do some magic!
        return new ResponseEntity<List<PietanzaDto>>(HttpStatus.OK);
    }

    public ResponseEntity<List<TavoloAccomodatoDto>> listaTavoliAccomodati( @NotNull@ApiParam(value = "Anche i tavoli in stato ordinato", required = true) @RequestParam(value = "statoOrdinato", required = true) Boolean statoOrdinato) {
        // do some magic!
    	//FIXME parametro statoOrdinato
    	
    	Page<TavoloAccomodato> tavoliAccomodatiPage = tavoloAccomodatoService.findAll(new PageRequest(0, Integer.MAX_VALUE));
    	List<TavoloAccomodato> tavoliAccomodati = tavoliAccomodatiPage.getContent();
    	List<TavoloAccomodatoDto> response = new ArrayList<TavoloAccomodatoDto>();
    	for (TavoloAccomodato tavoloAccomodato : tavoliAccomodati) {
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
    		
        	if(!tavoloAccomodato.getStato().equals(TavoloStato.LIBERATO))
        		response.add(tavoloAccomodatoDto);
    	}
        return new ResponseEntity<List<TavoloAccomodatoDto>>(response,HttpStatus.OK);
    }

}
