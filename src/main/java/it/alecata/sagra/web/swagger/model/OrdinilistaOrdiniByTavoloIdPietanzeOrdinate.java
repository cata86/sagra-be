package it.alecata.sagra.web.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * OrdinilistaOrdiniByTavoloIdPietanzeOrdinate
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-25T09:45:13.933Z")

public class OrdinilistaOrdiniByTavoloIdPietanzeOrdinate   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("pietanza")
  private OrdinilistaOrdiniByTavoloIdPietanza pietanza = null;

  @JsonProperty("note")
  private String note = null;

  @JsonProperty("numSequenza")
  private Integer numSequenza = null;

  @JsonProperty("quantita")
  private Integer quantita = null;

  public OrdinilistaOrdiniByTavoloIdPietanzeOrdinate id(Long id) {
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

  public OrdinilistaOrdiniByTavoloIdPietanzeOrdinate pietanza(OrdinilistaOrdiniByTavoloIdPietanza pietanza) {
    this.pietanza = pietanza;
    return this;
  }

   /**
   * Get pietanza
   * @return pietanza
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OrdinilistaOrdiniByTavoloIdPietanza getPietanza() {
    return pietanza;
  }

  public void setPietanza(OrdinilistaOrdiniByTavoloIdPietanza pietanza) {
    this.pietanza = pietanza;
  }

  public OrdinilistaOrdiniByTavoloIdPietanzeOrdinate note(String note) {
    this.note = note;
    return this;
  }

   /**
   * Get note
   * @return note
  **/
  @ApiModelProperty(value = "")


  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public OrdinilistaOrdiniByTavoloIdPietanzeOrdinate numSequenza(Integer numSequenza) {
    this.numSequenza = numSequenza;
    return this;
  }

   /**
   * Get numSequenza
   * @return numSequenza
  **/
  @ApiModelProperty(value = "")


  public Integer getNumSequenza() {
    return numSequenza;
  }

  public void setNumSequenza(Integer numSequenza) {
    this.numSequenza = numSequenza;
  }

  public OrdinilistaOrdiniByTavoloIdPietanzeOrdinate quantita(Integer quantita) {
    this.quantita = quantita;
    return this;
  }

   /**
   * Get quantita
   * @return quantita
  **/
  @ApiModelProperty(value = "")


  public Integer getQuantita() {
    return quantita;
  }

  public void setQuantita(Integer quantita) {
    this.quantita = quantita;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrdinilistaOrdiniByTavoloIdPietanzeOrdinate ordinilistaOrdiniByTavoloIdPietanzeOrdinate = (OrdinilistaOrdiniByTavoloIdPietanzeOrdinate) o;
    return Objects.equals(this.id, ordinilistaOrdiniByTavoloIdPietanzeOrdinate.id) &&
        Objects.equals(this.pietanza, ordinilistaOrdiniByTavoloIdPietanzeOrdinate.pietanza) &&
        Objects.equals(this.note, ordinilistaOrdiniByTavoloIdPietanzeOrdinate.note) &&
        Objects.equals(this.numSequenza, ordinilistaOrdiniByTavoloIdPietanzeOrdinate.numSequenza) &&
        Objects.equals(this.quantita, ordinilistaOrdiniByTavoloIdPietanzeOrdinate.quantita);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, pietanza, note, numSequenza, quantita);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrdinilistaOrdiniByTavoloIdPietanzeOrdinate {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    pietanza: ").append(toIndentedString(pietanza)).append("\n");
    sb.append("    note: ").append(toIndentedString(note)).append("\n");
    sb.append("    numSequenza: ").append(toIndentedString(numSequenza)).append("\n");
    sb.append("    quantita: ").append(toIndentedString(quantita)).append("\n");
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

