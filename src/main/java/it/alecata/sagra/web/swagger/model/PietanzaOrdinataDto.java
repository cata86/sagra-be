package it.alecata.sagra.web.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PietanzaOrdinata
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-28T13:04:29.118Z")

public class PietanzaOrdinataDto   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("pietanza")
  private PietanzaDto pietanza = null;

  @JsonProperty("note")
  private String note = null;

  @JsonProperty("numSequenza")
  private Integer numSequenza = null;

  @JsonProperty("quantita")
  private Integer quantita = null;

  public PietanzaOrdinataDto id(Long id) {
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

  public PietanzaOrdinataDto pietanza(PietanzaDto pietanza) {
    this.pietanza = pietanza;
    return this;
  }

   /**
   * Get pietanza
   * @return pietanza
  **/
  @ApiModelProperty(value = "")

  @Valid

  public PietanzaDto getPietanza() {
    return pietanza;
  }

  public void setPietanza(PietanzaDto pietanza) {
    this.pietanza = pietanza;
  }

  public PietanzaOrdinataDto note(String note) {
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

  public PietanzaOrdinataDto numSequenza(Integer numSequenza) {
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

  public PietanzaOrdinataDto quantita(Integer quantita) {
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
    PietanzaOrdinataDto pietanzaOrdinata = (PietanzaOrdinataDto) o;
    return Objects.equals(this.id, pietanzaOrdinata.id) &&
        Objects.equals(this.pietanza, pietanzaOrdinata.pietanza) &&
        Objects.equals(this.note, pietanzaOrdinata.note) &&
        Objects.equals(this.numSequenza, pietanzaOrdinata.numSequenza) &&
        Objects.equals(this.quantita, pietanzaOrdinata.quantita);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, pietanza, note, numSequenza, quantita);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PietanzaOrdinata {\n");
    
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

