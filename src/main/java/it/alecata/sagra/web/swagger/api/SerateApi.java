/**
 * NOTE: This class is auto generated by the swagger code generator program (2.2.3).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package it.alecata.sagra.web.swagger.api;

import it.alecata.sagra.web.swagger.model.SerataDto;

import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-08-19T16:46:29.535+02:00")

@Api(value = "serate", description = "the serate API")
public interface SerateApi {

    @ApiOperation(value = "Apri serata", notes = "Apre la serata", response = SerataDto.class, tags={ "cassa", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = SerataDto.class),
        @ApiResponse(code = 400, message = "Errore parametri", response = Void.class) })
    
    @RequestMapping(value = "/api/serate/apriSerata",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<SerataDto> apriSerata(@ApiParam(value = "idSagra, codice, descrizione, data, personaApertura" ,required=true )  @Valid @RequestBody SerataDto body);


    @ApiOperation(value = "Cancella serata", notes = "Cancella serata", response = Void.class, tags={ "cassa", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Void.class),
        @ApiResponse(code = 400, message = "Errore parametri", response = Void.class) })
    
    @RequestMapping(value = "/api/serate/cancellaSerata",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Void> cancellaSerata( @NotNull@ApiParam(value = "Identificativo della serata", required = true) @RequestParam(value = "idSerata", required = true) Long idSerata);


    @ApiOperation(value = "Chiudi serata", notes = "Chiude la serata", response = SerataDto.class, tags={ "cassa", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = SerataDto.class),
        @ApiResponse(code = 400, message = "Errore parametri", response = Void.class) })
    
    @RequestMapping(value = "/api/serate/chiudiSerata",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<SerataDto> chiudiSerata(@ApiParam(value = "id, personaChiusura" ,required=true )  @Valid @RequestBody SerataDto body);


    @ApiOperation(value = "Lista Serate", notes = "Lista Serate", response = SerataDto.class, responseContainer = "List", tags={ "cassa", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = SerataDto.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Errore parametri", response = Void.class) })
    
    @RequestMapping(value = "/api/serate/listaSerata",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<SerataDto>> listaSerata( @NotNull@ApiParam(value = "Identificativo della sagra", required = true) @RequestParam(value = "idSagra", required = true) Long idSagra);


    @ApiOperation(value = "Modifiica Serata", notes = "Modifiica Serata", response = SerataDto.class, tags={ "cassa", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = SerataDto.class),
        @ApiResponse(code = 400, message = "Errore parametri", response = Void.class) })
    
    @RequestMapping(value = "/api/serate/modificaSerata",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<SerataDto> modificaSerata(@ApiParam(value = "serata" ,required=true )  @Valid @RequestBody SerataDto body);


    @ApiOperation(value = "Ristampa serata", notes = "Ristampa serata", response = SerataDto.class, tags={ "cassa", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = SerataDto.class),
        @ApiResponse(code = 400, message = "Errore parametri", response = Void.class) })
    
    @RequestMapping(value = "/api/serate/stampaSerata",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<SerataDto> stampaSerata( @NotNull@ApiParam(value = "Identificativo della serata", required = true) @RequestParam(value = "idSerata", required = true) Long idSerata);

}
