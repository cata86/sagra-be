package it.alecata.sagra.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A TavoloReale.
 */
@Entity
@Table(name = "tavolo_reale")
public class TavoloReale implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codice")
    private String codice;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "posti_max")
    private Integer postiMax;

    @Column(name = "posti_occupati")
    private Integer postiOccupati;

    @Column(name = "asporto")
    private Boolean asporto;

    @ManyToOne(optional = false)
    @NotNull
    private Sagra sagra;
    
    @OneToMany(mappedBy="tavoloReale")
    private List<TavoloAccomodato> tavoliAccomodati;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodice() {
        return codice;
    }

    public TavoloReale codice(String codice) {
        this.codice = codice;
        return this;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public TavoloReale descrizione(String descrizione) {
        this.descrizione = descrizione;
        return this;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Integer getPostiMax() {
        return postiMax;
    }

    public TavoloReale postiMax(Integer postiMax) {
        this.postiMax = postiMax;
        return this;
    }

    public void setPostiMax(Integer postiMax) {
        this.postiMax = postiMax;
    }

    public Integer getPostiOccupati() {
        return postiOccupati;
    }

    public TavoloReale postiOccupati(Integer postiOccupati) {
        this.postiOccupati = postiOccupati;
        return this;
    }

    public void setPostiOccupati(Integer postiOccupati) {
        this.postiOccupati = postiOccupati;
    }

    public Boolean isAsporto() {
        return asporto;
    }

    public TavoloReale asporto(Boolean asporto) {
        this.asporto = asporto;
        return this;
    }

    public void setAsporto(Boolean asporto) {
        this.asporto = asporto;
    }

    public Sagra getSagra() {
        return sagra;
    }

    public TavoloReale sagra(Sagra sagra) {
        this.sagra = sagra;
        return this;
    }

    public void setSagra(Sagra sagra) {
        this.sagra = sagra;
    }

    public List<TavoloAccomodato> getTavoliAccomodati() {
		return tavoliAccomodati;
	}

	public void setTavoliAccomodati(List<TavoloAccomodato> tavoliAccomodati) {
		this.tavoliAccomodati = tavoliAccomodati;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TavoloReale tavoloReale = (TavoloReale) o;
        if (tavoloReale.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tavoloReale.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TavoloReale{" +
            "id=" + getId() +
            ", codice='" + getCodice() + "'" +
            ", descrizione='" + getDescrizione() + "'" +
            ", postiMax='" + getPostiMax() + "'" +
            ", postiOccupati='" + getPostiOccupati() + "'" +
            ", asporto='" + isAsporto() + "'" +
            "}";
    }
}
