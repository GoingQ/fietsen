package be.vdab.fietsen.cursussen;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

/*@Entity
@DiscriminatorValue("G")
public class GroepsCursus extends Cursus {
    private LocalDate van;
    private LocalDate tot;

    public LocalDate getVan() {
        return van;
    }

    public LocalDate getTot() {
        return tot;
    }
}*/

//Table per subclass && //Table per concrete class
@Entity
@Table(name = "groepscursussen")
public class GroepsCursus extends Cursus {
    private LocalDate van;
    private LocalDate tot;

    public LocalDate getVan() {
        return van;
    }

    public LocalDate getTot() {
        return tot;
    }
}
