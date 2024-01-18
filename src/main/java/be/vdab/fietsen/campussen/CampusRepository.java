package be.vdab.fietsen.campussen;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampusRepository extends JpaRepository<Campus, Long> {
    //Je leerde dat je in een repository methods kan maken (findBy…, countBy…, existsBy…,
    //deleteBy…) waar Spring Data JPA een bijbehorende query verzint. Je kan in zo’n method naam
    //verwijzen naar private variabelen van het value object dat bij de entity hoort
    List<Campus> findByAdres_PostcodeBetweenOrderByAdres_Gemeente(int van, int tot);
}
