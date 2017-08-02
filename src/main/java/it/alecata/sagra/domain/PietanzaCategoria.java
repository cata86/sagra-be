package it.alecata.sagra.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A PietanzaCategoria.
 */
@Entity
@Table(name = "pietanza_categoria")
public class PietanzaCategoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codice")
    private String codice;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "descrizione_breve")
    private String descrizioneBreve;

    @Column(name = "nome_stampante")
    private String nomeStampante;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodice() {
        return codice;
    }

    public PietanzaCategoria codice(String codice) {
        this.codice = codice;
        return this;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public PietanzaCategoria descrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizioneBreve() {
        return descrizioneBreve;
    }

    public PietanzaCategoria descrizioneBreve(String descrizioneBreve) {
        this.descrizioneBreve = descrizioneBreve;
        return this;
    }

    public void setDescrizioneBreve(String descrizioneBreve) {
        this.descrizioneBreve = descrizioneBreve;
    }

    public String getNomeStampante() {
        return nomeStampante;
    }

    public PietanzaCategoria nomeStampante(String nomeStampante) {
        this.nomeStampante = nomeStampante;
        return this;
    }

    public void setNomeStampante(String nomeStampante) {
        this.nomeStampante = nomeStampante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PietanzaCategoria pietanzaCategoria = (PietanzaCategoria) o;
        if (pietanzaCategoria.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pietanzaCategoria.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PietanzaCategoria{" +
            "id=" + getId() +
            ", codice='" + getCodice() + "'" +
            ", descrizione='" + getDescrizione() + "'" +
            ", descrizioneBreve='" + getDescrizioneBreve() + "'" +
            ", nomeStampante='" + getNomeStampante() + "'" +
            "}";
    }
}
