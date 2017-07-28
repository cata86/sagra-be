package it.alecata.sagra.web.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Sagra
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-25T09:45:13.933Z")

public class SagraDto   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("nome")
  private String nome = null;

  @JsonProperty("logo")
  private String logo = null;

  @JsonProperty("indirizzo")
  private String indirizzo = null;

  @JsonProperty("sequenzeAbilitate")
  private Boolean sequenzeAbilitate = false;

  public SagraDto id(Long id) {
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

  public SagraDto nome(String nome) {
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

  public SagraDto logo(String logo) {
    this.logo = logo;
    return this;
  }

   /**
   * Get logo
   * @return logo
  **/
  @ApiModelProperty(value = "")


  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public SagraDto indirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
    return this;
  }

   /**
   * Get indirizzo
   * @return indirizzo
  **/
  @ApiModelProperty(value = "")


  public String getIndirizzo() {
    return indirizzo;
  }

  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }

  public SagraDto sequenzeAbilitate(Boolean sequenzeAbilitate) {
    this.sequenzeAbilitate = sequenzeAbilitate;
    return this;
  }

   /**
   * Get sequenzeAbilitate
   * @return sequenzeAbilitate
  **/
  @ApiModelProperty(value = "")


  public Boolean getSequenzeAbilitate() {
    return sequenzeAbilitate;
  }

  public void setSequenzeAbilitate(Boolean sequenzeAbilitate) {
    this.sequenzeAbilitate = sequenzeAbilitate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SagraDto sagra = (SagraDto) o;
    return Objects.equals(this.id, sagra.id) &&
        Objects.equals(this.nome, sagra.nome) &&
        Objects.equals(this.logo, sagra.logo) &&
        Objects.equals(this.indirizzo, sagra.indirizzo) &&
        Objects.equals(this.sequenzeAbilitate, sagra.sequenzeAbilitate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nome, logo, indirizzo, sequenzeAbilitate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Sagra {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nome: ").append(toIndentedString(nome)).append("\n");
    sb.append("    logo: ").append(toIndentedString(logo)).append("\n");
    sb.append("    indirizzo: ").append(toIndentedString(indirizzo)).append("\n");
    sb.append("    sequenzeAbilitate: ").append(toIndentedString(sequenzeAbilitate)).append("\n");
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

