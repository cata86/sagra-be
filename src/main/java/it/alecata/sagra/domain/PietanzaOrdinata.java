package it.alecata.sagra.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A PietanzaOrdinata.
 */
@Entity
@Table(name = "pietanza_ordinata")
public class PietanzaOrdinata implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_sequenza")
    private Integer numeroSequenza;

    @Column(name = "quantita")
    private Integer quantita;

    @Column(name = "note")
    private String note;

    @ManyToOne(optional = false)
    @NotNull
    private Ordine ordine;

    @ManyToOne(optional = false)
    @NotNull
    private Pietanza pietanza;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroSequenza() {
        return numeroSequenza;
    }

    public PietanzaOrdinata numeroSequenza(Integer numeroSequenza) {
        this.numeroSequenza = numeroSequenza;
        return this;
    }

    public void setNumeroSequenza(Integer numeroSequenza) {
        this.numeroSequenza = numeroSequenza;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public PietanzaOrdinata quantita(Integer quantita) {
        this.quantita = quantita;
        return this;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public String getNote() {
        return note;
    }

    public PietanzaOrdinata note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public PietanzaOrdinata ordine(Ordine ordine) {
        this.ordine = ordine;
        return this;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    public Pietanza getPietanza() {
        return pietanza;
    }

    public PietanzaOrdinata pietanza(Pietanza pietanza) {
        this.pietanza = pietanza;
        return this;
    }

    public void setPietanza(Pietanza pietanza) {
        this.pietanza = pietanza;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PietanzaOrdinata pietanzaOrdinata = (PietanzaOrdinata) o;
        if (pietanzaOrdinata.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pietanzaOrdinata.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PietanzaOrdinata{" +
            "id=" + getId() +
            ", numeroSequenza='" + getNumeroSequenza() + "'" +
            ", quantita='" + getQuantita() + "'" +
            ", note='" + getNote() + "'" +
            "}";
    }
}
