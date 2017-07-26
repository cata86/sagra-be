package it.alecata.sagra.web.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Body1
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-25T09:45:13.933Z")

public class Body1   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("idSagra")
  private Long idSagra = null;

  @JsonProperty("codice")
  private String codice = null;

  @JsonProperty("descrizione")
  private String descrizione = null;

  @JsonProperty("data")
  private DateTime data = null;

  @JsonProperty("dataApertura")
  private DateTime dataApertura = null;

  @JsonProperty("dataChiusura")
  private DateTime dataChiusura = null;

  @JsonProperty("personaApertura")
  private String personaApertura = null;

  @JsonProperty("personaChiusura")
  private String personaChiusura = null;

  public Body1 id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Body1 idSagra(Long idSagra) {
    this.idSagra = idSagra;
    return this;
  }

   /**
   * Get idSagra
   * @return idSagra
  **/
  @ApiModelProperty(value = "")


  public Long getIdSagra() {
    return idSagra;
  }

  public void setIdSagra(Long idSagra) {
    this.idSagra = idSagra;
  }

  public Body1 codice(String codice) {
    this.codice = codice;
    return this;
  }

   /**
   * Get codice
   * @return codice
  **/
  @ApiModelProperty(value = "")


  public String getCodice() {
    return codice;
  }

  public void setCodice(String codice) {
    this.codice = codice;
  }

  public Body1 descrizione(String descrizione) {
    this.descrizione = descrizione;
    return this;
  }

   /**
   * Get descrizione
   * @return descrizione
  **/
  @ApiModelProperty(value = "")


  public String getDescrizione() {
    return descrizione;
  }

  public void setDescrizione(String descrizione) {
    this.descrizione = descrizione;
  }

  public Body1 data(DateTime data) {
    this.data = data;
    return this;
  }

   /**
   * Get data
   * @return data
  **/
  @ApiModelProperty(value = "")

  @Valid

  public DateTime getData() {
    return data;
  }

  public void setData(DateTime data) {
    this.data = data;
  }

  public Body1 dataApertura(DateTime dataApertura) {
    this.dataApertura = dataApertura;
    return this;
  }

   /**
   * Get dataApertura
   * @return dataApertura
  **/
  @ApiModelProperty(value = "")

  @Valid

  public DateTime getDataApertura() {
    return dataApertura;
  }

  public void setDataApertura(DateTime dataApertura) {
    this.dataApertura = dataApertura;
  }

  public Body1 dataChiusura(DateTime dataChiusura) {
    this.dataChiusura = dataChiusura;
    return this;
  }

   /**
   * Get dataChiusura
   * @return dataChiusura
  **/
  @ApiModelProperty(value = "")

  @Valid

  public DateTime getDataChiusura() {
    return dataChiusura;
  }

  public void setDataChiusura(DateTime dataChiusura) {
    this.dataChiusura = dataChiusura;
  }

  public Body1 personaApertura(String personaApertura) {
    this.personaApertura = personaApertura;
    return this;
  }

   /**
   * Get personaApertura
   * @return personaApertura
  **/
  @ApiModelProperty(value = "")


  public String getPersonaApertura() {
    return personaApertura;
  }

  public void setPersonaApertura(String personaApertura) {
    this.personaApertura = personaApertura;
  }

  public Body1 personaChiusura(String personaChiusura) {
    this.personaChiusura = personaChiusura;
    return this;
  }

   /**
   * Get personaChiusura
   * @return personaChiusura
  **/
  @ApiModelProperty(value = "")


  public String getPersonaChiusura() {
    return personaChiusura;
  }

  public void setPersonaChiusura(String personaChiusura) {
    this.personaChiusura = personaChiusura;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Body1 body1 = (Body1) o;
    return Objects.equals(this.id, body1.id) &&
        Objects.equals(this.idSagra, body1.idSagra) &&
        Objects.equals(this.codice, body1.codice) &&
        Objects.equals(this.descrizione, body1.descrizione) &&
        Objects.equals(this.data, body1.data) &&
        Objects.equals(this.dataApertura, body1.dataApertura) &&
        Objects.equals(this.dataChiusura, body1.dataChiusura) &&
        Objects.equals(this.personaApertura, body1.personaApertura) &&
        Objects.equals(this.personaChiusura, body1.personaChiusura);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, idSagra, codice, descrizione, data, dataApertura, dataChiusura, personaApertura, personaChiusura);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Body1 {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    idSagra: ").append(toIndentedString(idSagra)).append("\n");
    sb.append("    codice: ").append(toIndentedString(codice)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    dataApertura: ").append(toIndentedString(dataApertura)).append("\n");
    sb.append("    dataChiusura: ").append(toIndentedString(dataChiusura)).append("\n");
    sb.append("    personaApertura: ").append(toIndentedString(personaApertura)).append("\n");
    sb.append("    personaChiusura: ").append(toIndentedString(personaChiusura)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

