package it.alecata.sagra.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Serata.
 */
@Entity
@Table(name = "serata")
public class Serata implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codice")
    private String codice;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "data_apertura")
    private ZonedDateTime dataApertura;

    @Column(name = "data_chiusura")
    private ZonedDateTime dataChiusura;

    @Column(name = "persona_apertura")
    private String personaApertura;

    @Column(name = "persona_chiusura")
    private String personaChiusura;

    @ManyToOne(optional = false)
    @NotNull
    private Sagra sagra;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodice() {
        return codice;
    }

    public Serata codice(String codice) {
        this.codice = codice;
        return this;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Serata descrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDate getData() {
        return data;
    }

    public Serata data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public ZonedDateTime getDataApertura() {
        return dataApertura;
    }

    public Serata dataApertura(ZonedDateTime dataApertura) {
        this.dataApertura = dataApertura;
        return this;
    }

    public void setDataApertura(ZonedDateTime dataApertura) {
        this.dataApertura = dataApertura;
    }

    public ZonedDateTime getDataChiusura() {
        return dataChiusura;
    }

    public Serata dataChiusura(ZonedDateTime dataChiusura) {
        this.dataChiusura = dataChiusura;
        return this;
    }

    public void setDataChiusura(ZonedDateTime dataChiusura) {
        this.dataChiusura = dataChiusura;
    }

    public String getPersonaApertura() {
        return personaApertura;
    }

    public Serata personaApertura(String personaApertura) {
        this.personaApertura = personaApertura;
        return this;
    }

    public void setPersonaApertura(String personaApertura) {
        this.personaApertura = personaApertura;
    }

    public String getPersonaChiusura() {
        return personaChiusura;
    }

    public Serata personaChiusura(String personaChiusura) {
        this.personaChiusura = personaChiusura;
        return this;
    }

    public void setPersonaChiusura(String personaChiusura) {
        this.personaChiusura = personaChiusura;
    }

    public Sagra getSagra() {
        return sagra;
    }

    public Serata sagra(Sagra sagra) {
        this.sagra = sagra;
        return this;
    }

    public void setSagra(Sagra sagra) {
        this.sagra = sagra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Serata serata = (Serata) o;
        if (serata.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), serata.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Serata{" +
            "id=" + getId() +
            ", codice='" + getCodice() + "'" +
            ", descrizione='" + getDescrizione() + "'" +
            ", data='" + getData() + "'" +
            ", dataApertura='" + getDataApertura() + "'" +
            ", dataChiusura='" + getDataChiusura() + "'" +
            ", personaApertura='" + getPersonaApertura() + "'" +
            ", personaChiusura='" + getPersonaChiusura() + "'" +
            "}";
    }
}
