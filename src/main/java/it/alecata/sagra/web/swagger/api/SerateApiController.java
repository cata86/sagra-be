package it.alecata.sagra.web.swagger.api;

import it.alecata.sagra.domain.Sagra;
import it.alecata.sagra.domain.Serata;
import it.alecata.sagra.domain.TavoloAccomodato;
import it.alecata.sagra.domain.TavoloReale;
import it.alecata.sagra.service.SagraService;
import it.alecata.sagra.service.SerataService;
import it.alecata.sagra.web.swagger.model.SerataDto;
import it.alecata.sagra.web.swagger.model.SerataDto.StatoEnum;
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

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-12T20:01:54.092+02:00")

@Controller
public class SerateApiController implements SerateApi {


    private final SerataService serataService;
    
    private final SagraService sagraService;

    public SerateApiController(SerataService serataService, SagraService sagraService) {
        this.serataService = serataService;
        this.sagraService = sagraService;
    }

    public ResponseEntity<SerataDto> apriSerata(@ApiParam(value = "idSagra, codice, descrizione, data, personaApertura" ,required=true )  @Valid @RequestBody SerataDto body) {
    	Serata serata = serataService.findLastSerata();
    	if(serata!=null)
    		return new ResponseEntity<SerataDto>(HttpStatus.BAD_REQUEST);
    	
    	List<Sagra> sagre = sagraService.findAll();
    	
    	serata = new Serata();
    	serata.setCodice(body.getCodice());	
    	DateTime dataAperturaDT = body.getData().withZone(DateTimeZone.getDefault());
    	serata.setData(LocalDate.of(dataAperturaDT.getYear(), dataAperturaDT.getMonthOfYear(), dataAperturaDT.getDayOfMonth()));
    	serata.setDataApertura(ZonedDateTime.now(ZoneId.systemDefault()));
    	serata.setDescrizione(body.getDescrizione());
    	serata.setPersonaApertura(body.getPersonaApertura());
    	serata.setSagra(sagre.get(0));
    	serataService.save(serata);
    	SerataDto serataDto = serataToSerataDto(serata);
    	
    	return new ResponseEntity<SerataDto>(serataDto,HttpStatus.OK);
    }

    public ResponseEntity<SerataDto> chiudiSerata(@ApiParam(value = "id, personaChiusura" ,required=true )  @Valid @RequestBody SerataDto body) {
        // do some magic!
    	Serata serata = serataService.findOne(body.getId());
    	
    	serata.setDataChiusura(ZonedDateTime.now(ZoneId.systemDefault()));
    	serata.setPersonaChiusura(body.getPersonaChiusura());
    	serataService.save(serata);
    	
    	SerataDto serataDto = serataToSerataDto(serata);
    	
        return new ResponseEntity<SerataDto>(serataDto,HttpStatus.OK);
    }

    public ResponseEntity<List<SerataDto>> listaSerata( @NotNull@ApiParam(value = "Identificativo della sagra", required = true) @RequestParam(value = "idSagra", required = true) Boolean idSagra) {
    	Page<Serata> seratePage = serataService.findAll(new PageRequest(0, Integer.MAX_VALUE));
    	List<Serata> serate = seratePage.getContent();
    	List<SerataDto> response = new ArrayList<SerataDto>();
    	for(Serata serata : serate){
    		response.add(serataToSerataDto(serata));
    	}
    	
        return new ResponseEntity<List<SerataDto>>(HttpStatus.OK);
    }

    public ResponseEntity<SerataDto> modificaSerata(@ApiParam(value = "serata" ,required=true )  @Valid @RequestBody SerataDto body) {
        // do some magic!
    	
    	Serata serata = serataService.findOne(body.getId());
    	serata.setCodice(body.getCodice());	
    	if(body.getData()!=null){
	    	DateTime dataAperturaDT = body.getData().withZone(DateTimeZone.getDefault());
	    	serata.setData(LocalDate.of(dataAperturaDT.getYear(), dataAperturaDT.getMonthOfYear(), dataAperturaDT.getDayOfMonth()));
    	}
    	if(body.getDataApertura()!=null){
    		serata.setDataApertura(ZonedDateTime.ofInstant(Instant.ofEpochMilli(body.getDataApertura().getMillis()), ZoneId.systemDefault()));
    	}
    	if(body.getDataChiusura()!=null){
    		serata.setDataChiusura(ZonedDateTime.ofInstant(Instant.ofEpochMilli(body.getDataChiusura().getMillis()), ZoneId.systemDefault()));
    	}
    	serata.setDescrizione(body.getDescrizione());
    	serata.setPersonaApertura(body.getPersonaApertura());
    	serata.setPersonaChiusura(body.getPersonaChiusura());
    	serataService.save(serata);
    	SerataDto serataDto = serataToSerataDto(serata);
    	
        return new ResponseEntity<SerataDto>(serataDto,HttpStatus.OK);
    }
    
    private SerataDto serataToSerataDto(Serata serata){
    	SerataDto serataDto = new SerataDto();
    	serataDto.setId(serata.getId());
    	serataDto.setCodice(serata.getCodice());
    	serataDto.setData(new DateTime(serata.getData(), DateTimeZone.getDefault()));
    	serataDto.setDataApertura(new DateTime(serata.getDataApertura().toInstant().toEpochMilli(), DateTimeZone.getDefault()));
    	serataDto.setDataChiusura(new DateTime(serata.getDataChiusura().toInstant().toEpochMilli(), DateTimeZone.getDefault()));
    	serataDto.setDescrizione(serata.getDescrizione());
    	serataDto.setPersonaApertura(serata.getPersonaApertura());
    	serataDto.setPersonaChiusura(serata.getPersonaChiusura());
    	if(serata.getDataChiusura()==null)
    		serataDto.setStato(StatoEnum.APERTA);
    	else
    		serataDto.setStato(StatoEnum.CHIUSA);
    	return serataDto;
    }
    
}
