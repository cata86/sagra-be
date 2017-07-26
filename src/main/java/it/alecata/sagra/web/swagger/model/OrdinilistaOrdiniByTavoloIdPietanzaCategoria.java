package it.alecata.sagra.web.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * OrdinilistaOrdiniByTavoloIdPietanzaCategoria
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-25T09:45:13.933Z")

public class OrdinilistaOrdiniByTavoloIdPietanzaCategoria   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("codice")
  private String codice = null;

  @JsonProperty("descrizione")
  private String descrizione = null;

  @JsonProperty("descrizioneBreve")
  private String descrizioneBreve = null;

  @JsonProperty("nomeStampante")
  private String nomeStampante = null;

  public OrdinilistaOrdiniByTavoloIdPietanzaCategoria id(Long id) {
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

  public OrdinilistaOrdiniByTavoloIdPietanzaCategoria codice(String codice) {
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

  public OrdinilistaOrdiniByTavoloIdPietanzaCategoria descrizione(String descrizione) {
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

  public OrdinilistaOrdiniByTavoloIdPietanzaCategoria descrizioneBreve(String descrizioneBreve) {
    this.descrizioneBreve = descrizioneBreve;
    return this;
  }

   /**
   * Get descrizioneBreve
   * @return descrizioneBreve
  **/
  @ApiModelProperty(value = "")


  public String getDescrizioneBreve() {
    return descrizioneBreve;
  }

  public void setDescrizioneBreve(String descrizioneBreve) {
    this.descrizioneBreve = descrizioneBreve;
  }

  public OrdinilistaOrdiniByTavoloIdPietanzaCategoria nomeStampante(String nomeStampante) {
    this.nomeStampante = nomeStampante;
    return this;
  }

   /**
   * Get nomeStampante
   * @return nomeStampante
  **/
  @ApiModelProperty(value = "")


  public String getNomeStampante() {
    return nomeStampante;
  }

  public void setNomeStampante(String nomeStampante) {
    this.nomeStampante = nomeStampante;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrdinilistaOrdiniByTavoloIdPietanzaCategoria ordinilistaOrdiniByTavoloIdPietanzaCategoria = (OrdinilistaOrdiniByTavoloIdPietanzaCategoria) o;
    return Objects.equals(this.id, ordinilistaOrdiniByTavoloIdPietanzaCategoria.id) &&
        Objects.equals(this.codice, ordinilistaOrdiniByTavoloIdPietanzaCategoria.codice) &&
        Objects.equals(this.descrizione, ordinilistaOrdiniByTavoloIdPietanzaCategoria.descrizione) &&
        Objects.equals(this.descrizioneBreve, ordinilistaOrdiniByTavoloIdPietanzaCategoria.descrizioneBreve) &&
        Objects.equals(this.nomeStampante, ordinilistaOrdiniByTavoloIdPietanzaCategoria.nomeStampante);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, codice, descrizione, descrizioneBreve, nomeStampante);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrdinilistaOrdiniByTavoloIdPietanzaCategoria {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    codice: ").append(toIndentedString(codice)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    descrizioneBreve: ").append(toIndentedString(descrizioneBreve)).append("\n");
    sb.append("    nomeStampante: ").append(toIndentedString(nomeStampante)).append("\n");
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

