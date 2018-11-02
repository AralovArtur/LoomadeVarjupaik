package ee.ut.loomadevp.repository;

import ee.ut.loomadevp.model.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface StatsRepository extends JpaRepository<Stats, Long>  {
    //	@Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true ELSE false END FROM stats WHERE ip = :ip AND date > :date", nativeQuery = true)
    boolean existsStatsByIpAndDateAfter(@Param("ip") String ip, @Param("date") Date date);

    @Query(value = "SELECT browser, COUNT(browser) AS n FROM stats GROUP BY browser ORDER BY n DESC LIMIT 1", nativeQuery = true)
    String popularBrowser();

    List<Stats> findAll();
}
