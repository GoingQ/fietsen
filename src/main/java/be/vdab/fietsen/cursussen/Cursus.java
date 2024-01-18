package be.vdab.fietsen.cursussen;

import jakarta.persistence.*;

import java.util.UUID;

//Table per class hiërachy (cursussen.sql)
/*@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "cursussen")
@DiscriminatorColumn(name = "soort")
abstract class Cursus {
    @Id
    private long id;
    private String naam;

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}*/

//Table per subclass
/*@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "cursussen")
abstract class Cursus {
    @Id
    private long id;
    private String naam;

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}*/

//Table per concrete class
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract class Cursus {
    @Id
    private UUID id;
    private String naam;

    public UUID getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}