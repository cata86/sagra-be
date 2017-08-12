package it.alecata.sagra.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import it.alecata.sagra.domain.enumeration.TavoloStato;

/**
 * A TavoloAccomodato.
 */
@Entity
@Table(name = "tavolo_accomodato")
public class TavoloAccomodato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codice")
    private String codice;
    
    @Column(name = "nome_asporto")
    private String nomeAsporto;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "num_coperti")
    private Integer numCoperti;

    @Enumerated(EnumType.STRING)
    @Column(name = "stato")
    private TavoloStato stato;

    @Column(name = "accomodato_orario")
    private ZonedDateTime accomodatoOrario;

    @Column(name = "accomodato_persona")
    private String accomodatoPersona;

    @Column(name = "in_ordinazione_orario")
    private ZonedDateTime inOrdinazioneOrario;

    @Column(name = "in_ordinazione_persona")
    private String inOrdinazionePersona;

    @Column(name = "ordinazione_orario")
    private ZonedDateTime ordinazioneOrario;

    @Column(name = "ordinazione_persona")
    private String ordinazionePersona;

    @Column(name = "liberato_orario")
    private ZonedDateTime liberatoOrario;

    @Column(name = "liberato_persona")
    private String liberatoPersona;

    @Column(name = "asporto")
    private Boolean asporto;

    @ManyToOne(optional = false)
    @NotNull
    private Serata serata;

    @ManyToOne(optional = false)
    @NotNull
    private TavoloReale tavoloReale;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodice() {
        return codice;
    }

    public TavoloAccomodato codice(String codice) {
        this.codice = codice;
        return this;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getNomeAsporto() {
		return nomeAsporto;
	}

	public void setNomeAsporto(String nomeAsporto) {
		this.nomeAsporto = nomeAsporto;
	}

	public String getDescrizione() {
        return descrizione;
    }

    public TavoloAccomodato descrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Integer getNumCoperti() {
        return numCoperti;
    }

    public TavoloAccomodato numCoperti(Integer numCoperti) {
        this.numCoperti = numCoperti;
        return this;
    }

    public void setNumCoperti(Integer numCoperti) {
        this.numCoperti = numCoperti;
    }

    public TavoloStato getStato() {
        return stato;
    }

    public TavoloAccomodato stato(TavoloStato stato) {
        this.stato = stato;
        return this;
    }

    public void setStato(TavoloStato stato) {
        this.stato = stato;
    }

    public ZonedDateTime getAccomodatoOrario() {
        return accomodatoOrario;
    }

    public TavoloAccomodato accomodatoOrario(ZonedDateTime accomodatoOrario) {
        this.accomodatoOrario = accomodatoOrario;
        return this;
    }

    public void setAccomodatoOrario(ZonedDateTime accomodatoOrario) {
        this.accomodatoOrario = accomodatoOrario;
    }

    public String getAccomodatoPersona() {
        return accomodatoPersona;
    }

    public TavoloAccomodato accomodatoPersona(String accomodatoPersona) {
        this.accomodatoPersona = accomodatoPersona;
        return this;
    }

    public void setAccomodatoPersona(String accomodatoPersona) {
        this.accomodatoPersona = accomodatoPersona;
    }

    public ZonedDateTime getInOrdinazioneOrario() {
        return inOrdinazioneOrario;
    }

    public TavoloAccomodato inOrdinazioneOrario(ZonedDateTime inOrdinazioneOrario) {
        this.inOrdinazioneOrario = inOrdinazioneOrario;
        return this;
    }

    public void setInOrdinazioneOrario(ZonedDateTime inOrdinazioneOrario) {
        this.inOrdinazioneOrario = inOrdinazioneOrario;
    }

    public String getInOrdinazionePersona() {
        return inOrdinazionePersona;
    }

    public TavoloAccomodato inOrdinazionePersona(String inOrdinazionePersona) {
        this.inOrdinazionePersona = inOrdinazionePersona;
        return this;
    }

    public void setInOrdinazionePersona(String inOrdinazionePersona) {
        this.inOrdinazionePersona = inOrdinazionePersona;
    }

    public ZonedDateTime getOrdinazioneOrario() {
        return ordinazioneOrario;
    }

    public TavoloAccomodato ordinazioneOrario(ZonedDateTime ordinazioneOrario) {
        this.ordinazioneOrario = ordinazioneOrario;
        return this;
    }

    public void setOrdinazioneOrario(ZonedDateTime ordinazioneOrario) {
        this.ordinazioneOrario = ordinazioneOrario;
    }

    public String getOrdinazionePersona() {
        return ordinazionePersona;
    }

    public TavoloAccomodato ordinazionePersona(String ordinazionePersona) {
        this.ordinazionePersona = ordinazionePersona;
        return this;
    }

    public void setOrdinazionePersona(String ordinazionePersona) {
        this.ordinazionePersona = ordinazionePersona;
    }

    public ZonedDateTime getLiberatoOrario() {
        return liberatoOrario;
    }

    public TavoloAccomodato liberatoOrario(ZonedDateTime liberatoOrario) {
        this.liberatoOrario = liberatoOrario;
        return this;
    }

    public void setLiberatoOrario(ZonedDateTime liberatoOrario) {
        this.liberatoOrario = liberatoOrario;
    }

    public String getLiberatoPersona() {
        return liberatoPersona;
    }

    public TavoloAccomodato liberatoPersona(String liberatoPersona) {
        this.liberatoPersona = liberatoPersona;
        return this;
    }

    public void setLiberatoPersona(String liberatoPersona) {
        this.liberatoPersona = liberatoPersona;
    }

    public Boolean isAsporto() {
        return asporto;
    }

    public TavoloAccomodato asporto(Boolean asporto) {
        this.asporto = asporto;
        return this;
    }

    public void setAsporto(Boolean asporto) {
        this.asporto = asporto;
    }

    public Serata getSerata() {
        return serata;
    }

    public TavoloAccomodato serata(Serata serata) {
        this.serata = serata;
        return this;
    }

    public void setSerata(Serata serata) {
        this.serata = serata;
    }

    public TavoloReale getTavoloReale() {
        return tavoloReale;
    }

    public TavoloAccomodato tavoloReale(TavoloReale tavoloReale) {
        this.tavoloReale = tavoloReale;
        return this;
    }

    public void setTavoloReale(TavoloReale tavoloReale) {
        this.tavoloReale = tavoloReale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TavoloAccomodato tavoloAccomodato = (TavoloAccomodato) o;
        if (tavoloAccomodato.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tavoloAccomodato.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TavoloAccomodato{" +
            "id=" + getId() +
            ", codice='" + getCodice() + "'" +
            ", descrizione='" + getDescrizione() + "'" +
            ", numCoperti='" + getNumCoperti() + "'" +
            ", stato='" + getStato() + "'" +
            ", accomodatoOrario='" + getAccomodatoOrario() + "'" +
            ", accomodatoPersona='" + getAccomodatoPersona() + "'" +
            ", inOrdinazioneOrario='" + getInOrdinazioneOrario() + "'" +
            ", inOrdinazionePersona='" + getInOrdinazionePersona() + "'" +
            ", ordinazioneOrario='" + getOrdinazioneOrario() + "'" +
            ", ordinazionePersona='" + getOrdinazionePersona() + "'" +
            ", liberatoOrario='" + getLiberatoOrario() + "'" +
            ", liberatoPersona='" + getLiberatoPersona() + "'" +
            ", asporto='" + isAsporto() + "'" +
            "}";
    }
}
