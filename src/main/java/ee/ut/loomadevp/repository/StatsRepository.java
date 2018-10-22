package ee.ut.loomadevp.repository;

import ee.ut.loomadevp.model.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StatsRepository extends JpaRepository<Stats, Long>  {
    @Query(value = "SELECT browser, COUNT(browser) AS b FROM stats GROUP BY browser ORDER BY b DESC LIMIT 1", nativeQuery = true)
    String popularBrowser();
}
