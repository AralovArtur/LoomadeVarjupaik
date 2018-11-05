package ee.ut.loomadevp.repository;

import ee.ut.loomadevp.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    //SELECT * FROM loomad
    List<Animal> findAll();

    @Modifying
    @Transactional
    @Query(value="INSERT INTO loomad (liik, vanus, linn, pilt) VALUES (?, ?, ?, ?)", nativeQuery = true)
    void addAnimal(@Param("liik") String liik, @Param("vanus") int vanus, @Param("linn") String linn, @Param("pilt") String pilt);
}
