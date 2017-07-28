package it.alecata.sagra.web.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Ordine
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-28T13:04:29.118Z")

public class OrdineDto   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("idTavoloAccomodato")
  private Long idTavoloAccomodato = null;

  @JsonProperty("personaOrdine")
  private String personaOrdine = null;

  @JsonProperty("data")
  private Integer data = null;

  @JsonProperty("pietanzeOrdinate")
  private List<PietanzaOrdinataDto> pietanzeOrdinate = null;

  @JsonProperty("numCoperti")
  private Integer numCoperti = null;

  @JsonProperty("totale")
  private Float totale = null;

  @JsonProperty("quotaPersona")
  private Float quotaPersona = null;

  @JsonProperty("asporto")
  private Boolean asporto = false;

  public OrdineDto id(Long id) {
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

  public OrdineDto idTavoloAccomodato(Long idTavoloAccomodato) {
    this.idTavoloAccomodato = idTavoloAccomodato;
    return this;
  }

   /**
   * Get idTavoloAccomodato
   * @return idTavoloAccomodato
  **/
  @ApiModelProperty(value = "")


  public Long getIdTavoloAccomodato() {
    return idTavoloAccomodato;
  }

  public void setIdTavoloAccomodato(Long idTavoloAccomodato) {
    this.idTavoloAccomodato = idTavoloAccomodato;
  }

  public OrdineDto personaOrdine(String personaOrdine) {
    this.personaOrdine = personaOrdine;
    return this;
  }

   /**
   * Get personaOrdine
   * @return personaOrdine
  **/
  @ApiModelProperty(value = "")


  public String getPersonaOrdine() {
    return personaOrdine;
  }

  public void setPersonaOrdine(String personaOrdine) {
    this.personaOrdine = personaOrdine;
  }

  public OrdineDto data(Integer data) {
    this.data = data;
    return this;
  }

   /**
   * Get data
   * @return data
  **/
  @ApiModelProperty(value = "")


  public Integer getData() {
    return data;
  }

  public void setData(Integer data) {
    this.data = data;
  }

  public OrdineDto pietanzeOrdinate(List<PietanzaOrdinataDto> pietanzeOrdinate) {
    this.pietanzeOrdinate = pietanzeOrdinate;
    return this;
  }

  public OrdineDto addPietanzeOrdinateItem(PietanzaOrdinataDto pietanzeOrdinateItem) {
    if (this.pietanzeOrdinate == null) {
      this.pietanzeOrdinate = new ArrayList<PietanzaOrdinataDto>();
    }
    this.pietanzeOrdinate.add(pietanzeOrdinateItem);
    return this;
  }

   /**
   * Get pietanzeOrdinate
   * @return pietanzeOrdinate
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<PietanzaOrdinataDto> getPietanzeOrdinate() {
    return pietanzeOrdinate;
  }

  public void setPietanzeOrdinate(List<PietanzaOrdinataDto> pietanzeOrdinate) {
    this.pietanzeOrdinate = pietanzeOrdinate;
  }

  public OrdineDto numCoperti(Integer numCoperti) {
    this.numCoperti = numCoperti;
    return this;
  }

   /**
   * Get numCoperti
   * @return numCoperti
  **/
  @ApiModelProperty(value = "")


  public Integer getNumCoperti() {
    return numCoperti;
  }

  public void setNumCoperti(Integer numCoperti) {
    this.numCoperti = numCoperti;
  }

  public OrdineDto totale(Float totale) {
    this.totale = totale;
    return this;
  }

   /**
   * Get totale
   * @return totale
  **/
  @ApiModelProperty(value = "")


  public Float getTotale() {
    return totale;
  }

  public void setTotale(Float totale) {
    this.totale = totale;
  }

  public OrdineDto quotaPersona(Float quotaPersona) {
    this.quotaPersona = quotaPersona;
    return this;
  }

   /**
   * Get quotaPersona
   * @return quotaPersona
  **/
  @ApiModelProperty(value = "")


  public Float getQuotaPersona() {
    return quotaPersona;
  }

  public void setQuotaPersona(Float quotaPersona) {
    this.quotaPersona = quotaPersona;
  }

  public OrdineDto asporto(Boolean asporto) {
    this.asporto = asporto;
    return this;
  }

   /**
   * Get asporto
   * @return asporto
  **/
  @ApiModelProperty(value = "")


  public Boolean getAsporto() {
    return asporto;
  }

  public void setAsporto(Boolean asporto) {
    this.asporto = asporto;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrdineDto ordine = (OrdineDto) o;
    return Objects.equals(this.id, ordine.id) &&
        Objects.equals(this.idTavoloAccomodato, ordine.idTavoloAccomodato) &&
        Objects.equals(this.personaOrdine, ordine.personaOrdine) &&
        Objects.equals(this.data, ordine.data) &&
        Objects.equals(this.pietanzeOrdinate, ordine.pietanzeOrdinate) &&
        Objects.equals(this.numCoperti, ordine.numCoperti) &&
        Objects.equals(this.totale, ordine.totale) &&
        Objects.equals(this.quotaPersona, ordine.quotaPersona) &&
        Objects.equals(this.asporto, ordine.asporto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, idTavoloAccomodato, personaOrdine, data, pietanzeOrdinate, numCoperti, totale, quotaPersona, asporto);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Ordine {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    idTavoloAccomodato: ").append(toIndentedString(idTavoloAccomodato)).append("\n");
    sb.append("    personaOrdine: ").append(toIndentedString(personaOrdine)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("    pietanzeOrdinate: ").append(toIndentedString(pietanzeOrdinate)).append("\n");
    sb.append("    numCoperti: ").append(toIndentedString(numCoperti)).append("\n");
    sb.append("    totale: ").append(toIndentedString(totale)).append("\n");
    sb.append("    quotaPersona: ").append(toIndentedString(quotaPersona)).append("\n");
    sb.append("    asporto: ").append(toIndentedString(asporto)).append("\n");
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

