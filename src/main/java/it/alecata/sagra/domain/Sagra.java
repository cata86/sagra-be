package it.alecata.sagra.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Sagra.
 */
@Entity
@Table(name = "sagra")
public class Sagra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Lob
    @Column(name = "logo")
    private byte[] logo;

    @Column(name = "logo_content_type")
    private String logoContentType;

    @Column(name = "indirizzo")
    private String indirizzo;

    @Column(name = "piva")
    private String piva;

    @Column(name = "testata_scontrino")
    private String testataScontrino;

    @Column(name = "footer_scontrino")
    private String footerScontrino;

    @Column(name = "sequenze_abilitate")
    private Boolean sequenzeAbilitate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Sagra nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getLogo() {
        return logo;
    }

    public Sagra logo(byte[] logo) {
        this.logo = logo;
        return this;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getLogoContentType() {
        return logoContentType;
    }

    public Sagra logoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
        return this;
    }

    public void setLogoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public Sagra indirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
        return this;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getPiva() {
        return piva;
    }

    public Sagra piva(String piva) {
        this.piva = piva;
        return this;
    }

    public void setPiva(String piva) {
        this.piva = piva;
    }

    public String getTestataScontrino() {
        return testataScontrino;
    }

    public Sagra testataScontrino(String testataScontrino) {
        this.testataScontrino = testataScontrino;
        return this;
    }

    public void setTestataScontrino(String testataScontrino) {
        this.testataScontrino = testataScontrino;
    }

    public String getFooterScontrino() {
        return footerScontrino;
    }

    public Sagra footerScontrino(String footerScontrino) {
        this.footerScontrino = footerScontrino;
        return this;
    }

    public void setFooterScontrino(String footerScontrino) {
        this.footerScontrino = footerScontrino;
    }

    public Boolean isSequenzeAbilitate() {
        return sequenzeAbilitate;
    }

    public Sagra sequenzeAbilitate(Boolean sequenzeAbilitate) {
        this.sequenzeAbilitate = sequenzeAbilitate;
        return this;
    }

    public void setSequenzeAbilitate(Boolean sequenzeAbilitate) {
        this.sequenzeAbilitate = sequenzeAbilitate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sagra sagra = (Sagra) o;
        if (sagra.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sagra.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Sagra{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", logo='" + getLogo() + "'" +
            ", logoContentType='" + logoContentType + "'" +
            ", indirizzo='" + getIndirizzo() + "'" +
            ", piva='" + getPiva() + "'" +
            ", testataScontrino='" + getTestataScontrino() + "'" +
            ", footerScontrino='" + getFooterScontrino() + "'" +
            ", sequenzeAbilitate='" + isSequenzeAbilitate() + "'" +
            "}";
    }
}
