package it.alecata.sagra.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Ordine.
 */
@Entity
@Table(name = "ordine")
public class Ordine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "persona_ordine")
    private String personaOrdine;

    @Column(name = "data_ordine")
    private ZonedDateTime dataOrdine;

    @Column(name = "numero_coperti")
    private Integer numeroCoperti;

    @Column(name = "totale")
    private Float totale;

    @Column(name = "quota_persona")
    private Float quotaPersona;

    @Column(name = "asporto")
    private Boolean asporto;

    @ManyToOne(optional = false)
    @NotNull
    private TavoloAccomodato tavoloAccomodato;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonaOrdine() {
        return personaOrdine;
    }

    public Ordine personaOrdine(String personaOrdine) {
        this.personaOrdine = personaOrdine;
        return this;
    }

    public void setPersonaOrdine(String personaOrdine) {
        this.personaOrdine = personaOrdine;
    }

    public ZonedDateTime getDataOrdine() {
        return dataOrdine;
    }

    public Ordine dataOrdine(ZonedDateTime dataOrdine) {
        this.dataOrdine = dataOrdine;
        return this;
    }

    public void setDataOrdine(ZonedDateTime dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public Integer getNumeroCoperti() {
        return numeroCoperti;
    }

    public Ordine numeroCoperti(Integer numeroCoperti) {
        this.numeroCoperti = numeroCoperti;
        return this;
    }

    public void setNumeroCoperti(Integer numeroCoperti) {
        this.numeroCoperti = numeroCoperti;
    }

    public Float getTotale() {
        return totale;
    }

    public Ordine totale(Float totale) {
        this.totale = totale;
        return this;
    }

    public void setTotale(Float totale) {
        this.totale = totale;
    }

    public Float getQuotaPersona() {
        return quotaPersona;
    }

    public Ordine quotaPersona(Float quotaPersona) {
        this.quotaPersona = quotaPersona;
        return this;
    }

    public void setQuotaPersona(Float quotaPersona) {
        this.quotaPersona = quotaPersona;
    }

    public Boolean isAsporto() {
        return asporto;
    }

    public Ordine asporto(Boolean asporto) {
        this.asporto = asporto;
        return this;
    }

    public void setAsporto(Boolean asporto) {
        this.asporto = asporto;
    }

    public TavoloAccomodato getTavoloAccomodato() {
        return tavoloAccomodato;
    }

    public Ordine tavoloAccomodato(TavoloAccomodato tavoloAccomodato) {
        this.tavoloAccomodato = tavoloAccomodato;
        return this;
    }

    public void setTavoloAccomodato(TavoloAccomodato tavoloAccomodato) {
        this.tavoloAccomodato = tavoloAccomodato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ordine ordine = (Ordine) o;
        if (ordine.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ordine.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Ordine{" +
            "id=" + getId() +
            ", personaOrdine='" + getPersonaOrdine() + "'" +
            ", dataOrdine='" + getDataOrdine() + "'" +
            ", numeroCoperti='" + getNumeroCoperti() + "'" +
            ", totale='" + getTotale() + "'" +
            ", quotaPersona='" + getQuotaPersona() + "'" +
            ", asporto='" + isAsporto() + "'" +
            "}";
    }
}
