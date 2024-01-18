package be.vdab.fietsen.docenten;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "docenten")
public class Docent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String voornaam;
    private String familienaam;
    private BigDecimal wedde;
    private String emailAdres;
    @Enumerated(EnumType.STRING)
    private Geslacht geslacht;
    @Version
    private long versie;

    //21 VERZAMELING VALUE OBJECTEN MET EEN BASISTYPE
    @ElementCollection
    @CollectionTable(name = "bijnamen",
        joinColumns = @JoinColumn(name = "docentId") )
    @Column(name = "bijnaam")
    private Set<String> bijnamen;

    //Voor JPA volstaat het dat de default constructor de zichtbaarheid protected heeft.
    //Door deze constructor niet public te maken is hij niet zichtbaar in de rest van je code.
    protected Docent() {}

    public Docent(String voornaam, String familienaam, BigDecimal wedde, String emailAdres, Geslacht geslacht) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.wedde = wedde;
        this.emailAdres = emailAdres;
        this.geslacht = geslacht;
        //21 VERZAMELING VALUE OBJECTEN MET EEN BASISTYPE
        bijnamen = new LinkedHashSet<>();
    }

    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public BigDecimal getWedde() {
        return wedde;
    }

    public String getEmailAdres() {
        return emailAdres;
    }

    public Geslacht getGeslacht() {
        return geslacht;
    }

    //14. Wijzigen
    void setWedde(BigDecimal wedde) {
        this.wedde = wedde;
    }

    //21 VERZAMELING VALUE OBJECTEN MET EEN BASISTYPE
    void voegBijnaamToe(String bijnaam) {
        if ( ! bijnamen.add(bijnaam)) {
            throw new DocentHeeftDezeBijnaamAlException();
        }
    }
    void verwijderBijnaam(String bijnaam) {
        bijnamen.remove(bijnaam);
    }
    Set<String> getBijnamen() {
        //docent.getBijnamen().add("Polle pap"); mogelijk dus
        //Als je op die Set add of remove uitvoert, krijg je een UnsupportedOperationException.
        return Collections.unmodifiableSet(bijnamen);
    }
}
