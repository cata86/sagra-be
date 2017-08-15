package it.alecata.sagra.web.swagger.api;

import it.alecata.sagra.domain.Serata;
import it.alecata.sagra.domain.TavoloAccomodato;
import it.alecata.sagra.domain.TavoloReale;
import it.alecata.sagra.service.SerataService;
import it.alecata.sagra.web.swagger.model.SerataDto;
import it.alecata.sagra.web.swagger.model.TavoloAccomodatoDto;
import io.swagger.annotations.*;

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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-12T20:01:54.092+02:00")

@Controller
public class SerateApiController implements SerateApi {


    private final SerataService serataService;

    public SerateApiController(SerataService serataService) {
        this.serataService = serataService;
    }

    public ResponseEntity<SerataDto> apriSerata(@ApiParam(value = "idSagra, codice, descrizione, data, personaApertura" ,required=true )  @Valid @RequestBody SerataDto body) {
        // do some magic!
        return new ResponseEntity<SerataDto>(HttpStatus.OK);
    }

    public ResponseEntity<SerataDto> chiudiSerata(@ApiParam(value = "id, personaChiusura" ,required=true )  @Valid @RequestBody SerataDto body) {
        // do some magic!
        return new ResponseEntity<SerataDto>(HttpStatus.OK);
    }

    public ResponseEntity<List<SerataDto>> listaSerata( @NotNull@ApiParam(value = "Identificativo della sagra", required = true) @RequestParam(value = "idSagra", required = true) Boolean idSagra) {
    	Page<Serata> seratePage = serataService.findAll(new PageRequest(0, Integer.MAX_VALUE));
    	List<Serata> serate = seratePage.getContent();
    	List<SerataDto> response = new ArrayList<SerataDto>();
    	for(Serata serata : serate){
    		response.add(tavoloAccomodatoToDto(serata));
    	}
    	
        return new ResponseEntity<List<SerataDto>>(HttpStatus.OK);
    }

    public ResponseEntity<SerataDto> modificaSerata(@ApiParam(value = "serata" ,required=true )  @Valid @RequestBody SerataDto body) {
        // do some magic!
        return new ResponseEntity<SerataDto>(HttpStatus.OK);
    }
    
    private SerataDto tavoloAccomodatoToDto(Serata serata){
    	SerataDto serataDto = new SerataDto();
    	serataDto.setId(serata.getId());
    	serataDto.setCodice(serata.getCodice());
    	serataDto.setData(new DateTime(serata.getData(), DateTimeZone.getDefault()));
    	serataDto.setDataApertura(new DateTime(serata.getDataApertura().toInstant().toEpochMilli(), DateTimeZone.getDefault()));
    	serataDto.setDataChiusura(new DateTime(serata.getDataChiusura().toInstant().toEpochMilli(), DateTimeZone.getDefault()));
    	serataDto.setDescrizione(serata.getDescrizione());
    	serataDto.setPersonaApertura(serata.getPersonaApertura());
    	serataDto.setPersonaChiusura(serata.getPersonaChiusura());
    	return serataDto;
    }
    

}
