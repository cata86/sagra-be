package it.alecata.sagra.web.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * TavoloReale
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-25T09:45:13.933Z")

public class TavoloRealeDto   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("codice")
  private String codice = null;

  @JsonProperty("descrizione")
  private String descrizione = null;

  @JsonProperty("postiMax")
  private Integer postiMax = null;

  @JsonProperty("postiOccupati")
  private Integer postiOccupati = null;

  @JsonProperty("idSagra")
  private Long idSagra = null;

  @JsonProperty("asporto")
  private Boolean asporto = false;

  public TavoloRealeDto id(Long id) {
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

  public TavoloRealeDto codice(String codice) {
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

  public TavoloRealeDto descrizione(String descrizione) {
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

  public TavoloRealeDto postiMax(Integer postiMax) {
    this.postiMax = postiMax;
    return this;
  }

   /**
   * Get postiMax
   * @return postiMax
  **/
  @ApiModelProperty(value = "")


  public Integer getPostiMax() {
    return postiMax;
  }

  public void setPostiMax(Integer postiMax) {
    this.postiMax = postiMax;
  }

  public TavoloRealeDto postiOccupati(Integer postiOccupati) {
    this.postiOccupati = postiOccupati;
    return this;
  }

   /**
   * Get postiOccupati
   * @return postiOccupati
  **/
  @ApiModelProperty(value = "")


  public Integer getPostiOccupati() {
    return postiOccupati;
  }

  public void setPostiOccupati(Integer postiOccupati) {
    this.postiOccupati = postiOccupati;
  }

  public TavoloRealeDto idSagra(Long idSagra) {
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

  public TavoloRealeDto asporto(Boolean asporto) {
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
    TavoloRealeDto tavoloReale = (TavoloRealeDto) o;
    return Objects.equals(this.id, tavoloReale.id) &&
        Objects.equals(this.codice, tavoloReale.codice) &&
        Objects.equals(this.descrizione, tavoloReale.descrizione) &&
        Objects.equals(this.postiMax, tavoloReale.postiMax) &&
        Objects.equals(this.postiOccupati, tavoloReale.postiOccupati) &&
        Objects.equals(this.idSagra, tavoloReale.idSagra) &&
        Objects.equals(this.asporto, tavoloReale.asporto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, codice, descrizione, postiMax, postiOccupati, idSagra, asporto);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TavoloReale {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    codice: ").append(toIndentedString(codice)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    postiMax: ").append(toIndentedString(postiMax)).append("\n");
    sb.append("    postiOccupati: ").append(toIndentedString(postiOccupati)).append("\n");
    sb.append("    idSagra: ").append(toIndentedString(idSagra)).append("\n");
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

