package be.vdab.fietsen.campussen;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "campussen")
public class Campus {
    @Id
    private Long id;
    private String naam;
    @Embedded
    private Adres adres;

    //23 VERZAMELING VALUE OBJECTEN MET EEN EIGEN TYPE
    @ElementCollection
    @CollectionTable(name = "huurprijzen",
        joinColumns = @JoinColumn(name = "campusId"))
    @OrderBy("vanaf")
    private Set<Huurprijs> huurprijzen;

    public Campus(Long id, String naam, Adres adres) {
        this.id = id;
        this.naam = naam;
        this.adres = adres;
        //23 VERZAMELING VALUE OBJECTEN MET EEN EIGEN TYPE
        huurprijzen = new LinkedHashSet<Huurprijs>();
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

    //23 VERZAMELING VALUE OBJECTEN MET EEN EIGEN TYPE
    Set<Huurprijs> getHuurprijzen() {
        return Collections.unmodifiableSet(huurprijzen);
    }
}
