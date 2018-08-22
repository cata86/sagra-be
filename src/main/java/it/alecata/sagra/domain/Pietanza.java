package it.alecata.sagra.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Pietanza.
 */
@Entity
@Table(name = "pietanza")
public class Pietanza implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codice")
    private String codice;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "prezzo")
    private Float prezzo;
    
    @Column(name = "contatore")
    private Boolean contatore;
    
    @Column(name = "ordine")
    private Integer ordine;

    @ManyToOne(optional = false)
    @NotNull
    private PietanzaCategoria pietanzaCategoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodice() {
        return codice;
    }

    public Pietanza codice(String codice) {
        this.codice = codice;
        return this;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public Pietanza nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Pietanza descrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public Pietanza prezzo(Float prezzo) {
        this.prezzo = prezzo;
        return this;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }
    
    public Boolean getContatore() {
		return contatore;
	}

	public void setContatore(Boolean contatore) {
		this.contatore = contatore;
	}

	public Integer getOrdine() {
		return ordine;
	}

	public void setOrdine(Integer ordine) {
		this.ordine = ordine;
	}

	public PietanzaCategoria getPietanzaCategoria() {
        return pietanzaCategoria;
    }

    public Pietanza pietanzaCategoria(PietanzaCategoria pietanzaCategoria) {
        this.pietanzaCategoria = pietanzaCategoria;
        return this;
    }

    public void setPietanzaCategoria(PietanzaCategoria pietanzaCategoria) {
        this.pietanzaCategoria = pietanzaCategoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pietanza pietanza = (Pietanza) o;
        if (pietanza.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), pietanza.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Pietanza{" +
            "id=" + getId() +
            ", codice='" + getCodice() + "'" +
            ", nome='" + getNome() + "'" +
            ", descrizione='" + getDescrizione() + "'" +
            ", prezzo='" + getPrezzo() + "'" +
            ", ordine='" + getOrdine() + "'" +
            "}";
    }
}
