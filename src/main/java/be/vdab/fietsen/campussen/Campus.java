package be.vdab.fietsen.campussen;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "campussen")
public class Campus {
    @Id
    private Long id;
    private String naam;
    @Embedded
    private Adres adres;

    public Campus(Long id, String naam, Adres adres) {
        this.id = id;
        this.naam = naam;
        this.adres = adres;
    }

    protected Campus(){}

    public Long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }
}
