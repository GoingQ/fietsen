package be.vdab.fietsen.docenten;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DocentService {
    private final DocentRepository docentRepository;

    public DocentService(DocentRepository docentRepository) {
        this.docentRepository = docentRepository;
    }

    long findAantal() {
        return docentRepository.count();
    }
    List<Docent> findAll() {
        return docentRepository.findAll(Sort.by("familienaam"));
    }
    Optional<Docent> findById(long id) {
        return docentRepository.findById(id);
    }
    boolean existsById(long id) {
        return docentRepository.existsById(id);
    }


    @Transactional
    long create(NieuweDocent nieuweDocent) {
        try {
            var docent = new Docent(nieuweDocent.voornaam(), nieuweDocent.familienaam(),
                    nieuweDocent.wedde(), nieuweDocent.emailAdres(), nieuweDocent.geslacht());
            docentRepository.save(docent);
            return docent.getId();
        } catch (DataIntegrityViolationException ex) {
            throw new DocentBestaatAlException();
        }
    }

    @Transactional
    void delete(long id) {
        docentRepository.deleteById(id);
    }

   /* List<Docent> findByWedde(BigDecimal wedde) {
        return docentRepository.findByWedde(wedde);
    }*/

    List<Docent> findByWedde(BigDecimal wedde) {
        return docentRepository.findByWeddeOrderByFamilienaamAscVoornaamAsc(wedde);
    }

    Optional<Docent> findByEmailAdres(String emailAdres) {
        return docentRepository.findByEmailAdres(emailAdres);
    }

    int findAantalMetWedde(BigDecimal wedde) {
        return docentRepository.countByWedde(wedde);
    }

    List<Docent> findMetGrootsteWedde() {
        return docentRepository.findMetGrootsteWedde();
    }


    //13. DTO
    BigDecimal findGrootsteWedde() {
        return docentRepository.findGrootsteWedde();
    }

    List<EnkelNaam> findNamen() {
        return docentRepository.findNamen();
    }

    List<AantalDocentenPerWedde> findAantalDocentenPerWedde() {
        return docentRepository.findAantalDocentenPerWedde();
    }


    //14. Wijzigen
    @Transactional
    void wijzigWedde(long id, BigDecimal wedde) {
        docentRepository.findById(id)
                .orElseThrow(DocentNietGevondenException::new)
                .setWedde(wedde);
    }

    //15. Lock
    Optional<Docent> findAndLockById(long id) {
        return docentRepository.findAndLockById(id);
    }

    //17. Bulk Update
    @Transactional
    void algemeneOpslag(BigDecimal bedrag) {
        docentRepository.algemeneOpslag(bedrag);
    }

    //21 VERZAMELING VALUE OBJECTEN MET EEN BASISTYPE
    @Transactional
    void voegBijnaamToe(long id, String bijnaam) {
        docentRepository.findById(id)
                .orElseThrow(()-> new DocentNietGevondenException())
                .voegBijnaamToe(bijnaam);
    }
    @Transactional
    void verwijderBijnaam(long id, String bijnaam) {
        docentRepository.findById(id)
                .orElseThrow(DocentNietGevondenException::new)
                .verwijderBijnaam(bijnaam);
    }

    //22 N + 1 PROBLEEM
        //Een JPQL query met join fetch
    List<Docent> findAllMetBijnamen() {
        return docentRepository.findAllMetBijnamen();
    }
}
