package it.alecata.sagra.web.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * OrdinilistaOrdiniByTavoloIdPietanza
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-25T09:45:13.933Z")

public class OrdinilistaOrdiniByTavoloIdPietanza   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("categoria")
  private OrdinilistaOrdiniByTavoloIdPietanzaCategoria categoria = null;

  @JsonProperty("nome")
  private String nome = null;

  @JsonProperty("descrizione")
  private String descrizione = null;

  @JsonProperty("prezzo")
  private Float prezzo = null;

  public OrdinilistaOrdiniByTavoloIdPietanza id(Long id) {
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

  public OrdinilistaOrdiniByTavoloIdPietanza categoria(OrdinilistaOrdiniByTavoloIdPietanzaCategoria categoria) {
    this.categoria = categoria;
    return this;
  }

   /**
   * Get categoria
   * @return categoria
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OrdinilistaOrdiniByTavoloIdPietanzaCategoria getCategoria() {
    return categoria;
  }

  public void setCategoria(OrdinilistaOrdiniByTavoloIdPietanzaCategoria categoria) {
    this.categoria = categoria;
  }

  public OrdinilistaOrdiniByTavoloIdPietanza nome(String nome) {
    this.nome = nome;
    return this;
  }

   /**
   * Get nome
   * @return nome
  **/
  @ApiModelProperty(value = "")


  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public OrdinilistaOrdiniByTavoloIdPietanza descrizione(String descrizione) {
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

  public OrdinilistaOrdiniByTavoloIdPietanza prezzo(Float prezzo) {
    this.prezzo = prezzo;
    return this;
  }

   /**
   * Get prezzo
   * @return prezzo
  **/
  @ApiModelProperty(value = "")


  public Float getPrezzo() {
    return prezzo;
  }

  public void setPrezzo(Float prezzo) {
    this.prezzo = prezzo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrdinilistaOrdiniByTavoloIdPietanza ordinilistaOrdiniByTavoloIdPietanza = (OrdinilistaOrdiniByTavoloIdPietanza) o;
    return Objects.equals(this.id, ordinilistaOrdiniByTavoloIdPietanza.id) &&
        Objects.equals(this.categoria, ordinilistaOrdiniByTavoloIdPietanza.categoria) &&
        Objects.equals(this.nome, ordinilistaOrdiniByTavoloIdPietanza.nome) &&
        Objects.equals(this.descrizione, ordinilistaOrdiniByTavoloIdPietanza.descrizione) &&
        Objects.equals(this.prezzo, ordinilistaOrdiniByTavoloIdPietanza.prezzo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, categoria, nome, descrizione, prezzo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OrdinilistaOrdiniByTavoloIdPietanza {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    categoria: ").append(toIndentedString(categoria)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    prezzo: ").append(toIndentedString(prezzo)).append("\n");
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

