package it.alecata.sagra.web.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import it.alecata.sagra.web.swagger.model.TavoloAccomodato.StatoEnum;

import org.joda.time.DateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * InlineResponse2002
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-25T09:45:13.933Z")

public class InlineResponse2002   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("idSerata")
  private Long idSerata = null;

  @JsonProperty("idTavoloReale")
  private Long idTavoloReale = null;

  @JsonProperty("codice")
  private String codice = null;

  @JsonProperty("descrizione")
  private String descrizione = null;

  @JsonProperty("numCoperti")
  private Integer numCoperti = null;


  @JsonProperty("stato")
  private StatoEnum stato = null;

  @JsonProperty("accomodatoOrario")
  private DateTime accomodatoOrario = null;

  @JsonProperty("accomodatoPersona")
  private String accomodatoPersona = null;

  @JsonProperty("inOrdinazioneOrario")
  private DateTime inOrdinazioneOrario = null;

  @JsonProperty("inOrdinazionePersona")
  private String inOrdinazionePersona = null;

  @JsonProperty("ordinazioneOrario")
  private DateTime ordinazioneOrario = null;

  @JsonProperty("ordinazionePersona")
  private String ordinazionePersona = null;

  @JsonProperty("liberatoOrario")
  private DateTime liberatoOrario = null;

  @JsonProperty("liberatoPersona")
  private String liberatoPersona = null;

  @JsonProperty("asporto")
  private Boolean asporto = false;

  public InlineResponse2002 id(Long id) {
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

  public InlineResponse2002 idSerata(Long idSerata) {
    this.idSerata = idSerata;
    return this;
  }

   /**
   * Get idSerata
   * @return idSerata
  **/
  @ApiModelProperty(value = "")


  public Long getIdSerata() {
    return idSerata;
  }

  public void setIdSerata(Long idSerata) {
    this.idSerata = idSerata;
  }

  public InlineResponse2002 idTavoloReale(Long idTavoloReale) {
    this.idTavoloReale = idTavoloReale;
    return this;
  }

   /**
   * Get idTavoloReale
   * @return idTavoloReale
  **/
  @ApiModelProperty(value = "")


  public Long getIdTavoloReale() {
    return idTavoloReale;
  }

  public void setIdTavoloReale(Long idTavoloReale) {
    this.idTavoloReale = idTavoloReale;
  }

  public InlineResponse2002 codice(String codice) {
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

  public InlineResponse2002 descrizione(String descrizione) {
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

  public InlineResponse2002 numCoperti(Integer numCoperti) {
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

  public InlineResponse2002 stato(StatoEnum stato) {
    this.stato = stato;
    return this;
  }

   /**
   * Get stato
   * @return stato
  **/
  @ApiModelProperty(value = "")


  public StatoEnum getStato() {
    return stato;
  }

  public void setStato(StatoEnum stato) {
    this.stato = stato;
  }

  public InlineResponse2002 accomodatoOrario(DateTime accomodatoOrario) {
    this.accomodatoOrario = accomodatoOrario;
    return this;
  }

   /**
   * Get accomodatoOrario
   * @return accomodatoOrario
  **/
  @ApiModelProperty(value = "")

  @Valid

  public DateTime getAccomodatoOrario() {
    return accomodatoOrario;
  }

  public void setAccomodatoOrario(DateTime accomodatoOrario) {
    this.accomodatoOrario = accomodatoOrario;
  }

  public InlineResponse2002 accomodatoPersona(String accomodatoPersona) {
    this.accomodatoPersona = accomodatoPersona;
    return this;
  }

   /**
   * Get accomodatoPersona
   * @return accomodatoPersona
  **/
  @ApiModelProperty(value = "")


  public String getAccomodatoPersona() {
    return accomodatoPersona;
  }

  public void setAccomodatoPersona(String accomodatoPersona) {
    this.accomodatoPersona = accomodatoPersona;
  }

  public InlineResponse2002 inOrdinazioneOrario(DateTime inOrdinazioneOrario) {
    this.inOrdinazioneOrario = inOrdinazioneOrario;
    return this;
  }

   /**
   * Get inOrdinazioneOrario
   * @return inOrdinazioneOrario
  **/
  @ApiModelProperty(value = "")

  @Valid

  public DateTime getInOrdinazioneOrario() {
    return inOrdinazioneOrario;
  }

  public void setInOrdinazioneOrario(DateTime inOrdinazioneOrario) {
    this.inOrdinazioneOrario = inOrdinazioneOrario;
  }

  public InlineResponse2002 inOrdinazionePersona(String inOrdinazionePersona) {
    this.inOrdinazionePersona = inOrdinazionePersona;
    return this;
  }

   /**
   * Get inOrdinazionePersona
   * @return inOrdinazionePersona
  **/
  @ApiModelProperty(value = "")


  public String getInOrdinazionePersona() {
    return inOrdinazionePersona;
  }

  public void setInOrdinazionePersona(String inOrdinazionePersona) {
    this.inOrdinazionePersona = inOrdinazionePersona;
  }

  public InlineResponse2002 ordinazioneOrario(DateTime ordinazioneOrario) {
    this.ordinazioneOrario = ordinazioneOrario;
    return this;
  }

   /**
   * Get ordinazioneOrario
   * @return ordinazioneOrario
  **/
  @ApiModelProperty(value = "")

  @Valid

  public DateTime getOrdinazioneOrario() {
    return ordinazioneOrario;
  }

  public void setOrdinazioneOrario(DateTime ordinazioneOrario) {
    this.ordinazioneOrario = ordinazioneOrario;
  }

  public InlineResponse2002 ordinazionePersona(String ordinazionePersona) {
    this.ordinazionePersona = ordinazionePersona;
    return this;
  }

   /**
   * Get ordinazionePersona
   * @return ordinazionePersona
  **/
  @ApiModelProperty(value = "")


  public String getOrdinazionePersona() {
    return ordinazionePersona;
  }

  public void setOrdinazionePersona(String ordinazionePersona) {
    this.ordinazionePersona = ordinazionePersona;
  }

  public InlineResponse2002 liberatoOrario(DateTime liberatoOrario) {
    this.liberatoOrario = liberatoOrario;
    return this;
  }

   /**
   * Get liberatoOrario
   * @return liberatoOrario
  **/
  @ApiModelProperty(value = "")

  @Valid

  public DateTime getLiberatoOrario() {
    return liberatoOrario;
  }

  public void setLiberatoOrario(DateTime liberatoOrario) {
    this.liberatoOrario = liberatoOrario;
  }

  public InlineResponse2002 liberatoPersona(String liberatoPersona) {
    this.liberatoPersona = liberatoPersona;
    return this;
  }

   /**
   * Get liberatoPersona
   * @return liberatoPersona
  **/
  @ApiModelProperty(value = "")


  public String getLiberatoPersona() {
    return liberatoPersona;
  }

  public void setLiberatoPersona(String liberatoPersona) {
    this.liberatoPersona = liberatoPersona;
  }

  public InlineResponse2002 asporto(Boolean asporto) {
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
    InlineResponse2002 inlineResponse2002 = (InlineResponse2002) o;
    return Objects.equals(this.id, inlineResponse2002.id) &&
        Objects.equals(this.idSerata, inlineResponse2002.idSerata) &&
        Objects.equals(this.idTavoloReale, inlineResponse2002.idTavoloReale) &&
        Objects.equals(this.codice, inlineResponse2002.codice) &&
        Objects.equals(this.descrizione, inlineResponse2002.descrizione) &&
        Objects.equals(this.numCoperti, inlineResponse2002.numCoperti) &&
        Objects.equals(this.stato, inlineResponse2002.stato) &&
        Objects.equals(this.accomodatoOrario, inlineResponse2002.accomodatoOrario) &&
        Objects.equals(this.accomodatoPersona, inlineResponse2002.accomodatoPersona) &&
        Objects.equals(this.inOrdinazioneOrario, inlineResponse2002.inOrdinazioneOrario) &&
        Objects.equals(this.inOrdinazionePersona, inlineResponse2002.inOrdinazionePersona) &&
        Objects.equals(this.ordinazioneOrario, inlineResponse2002.ordinazioneOrario) &&
        Objects.equals(this.ordinazionePersona, inlineResponse2002.ordinazionePersona) &&
        Objects.equals(this.liberatoOrario, inlineResponse2002.liberatoOrario) &&
        Objects.equals(this.liberatoPersona, inlineResponse2002.liberatoPersona) &&
        Objects.equals(this.asporto, inlineResponse2002.asporto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, idSerata, idTavoloReale, codice, descrizione, numCoperti, stato, accomodatoOrario, accomodatoPersona, inOrdinazioneOrario, inOrdinazionePersona, ordinazioneOrario, ordinazionePersona, liberatoOrario, liberatoPersona, asporto);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse2002 {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    idSerata: ").append(toIndentedString(idSerata)).append("\n");
    sb.append("    idTavoloReale: ").append(toIndentedString(idTavoloReale)).append("\n");
    sb.append("    codice: ").append(toIndentedString(codice)).append("\n");
    sb.append("    descrizione: ").append(toIndentedString(descrizione)).append("\n");
    sb.append("    numCoperti: ").append(toIndentedString(numCoperti)).append("\n");
    sb.append("    stato: ").append(toIndentedString(stato)).append("\n");
    sb.append("    accomodatoOrario: ").append(toIndentedString(accomodatoOrario)).append("\n");
    sb.append("    accomodatoPersona: ").append(toIndentedString(accomodatoPersona)).append("\n");
    sb.append("    inOrdinazioneOrario: ").append(toIndentedString(inOrdinazioneOrario)).append("\n");
    sb.append("    inOrdinazionePersona: ").append(toIndentedString(inOrdinazionePersona)).append("\n");
    sb.append("    ordinazioneOrario: ").append(toIndentedString(ordinazioneOrario)).append("\n");
    sb.append("    ordinazionePersona: ").append(toIndentedString(ordinazionePersona)).append("\n");
    sb.append("    liberatoOrario: ").append(toIndentedString(liberatoOrario)).append("\n");
    sb.append("    liberatoPersona: ").append(toIndentedString(liberatoPersona)).append("\n");
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

