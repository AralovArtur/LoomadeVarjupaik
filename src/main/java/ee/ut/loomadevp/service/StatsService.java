package ee.ut.loomadevp.service;

import ee.ut.loomadevp.repository.StatsRepository;
import ee.ut.loomadevp.model.Stats;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class StatsService {

    private final StatsRepository statsRepository;

    public StatsService(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    public void saveUserAgent(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        String browser = userAgent.getBrowser().getName();
        String operatingSystem = userAgent.getOperatingSystem().getName();

        Stats stats = new Stats();
        stats.setIp(remoteAddr);
        stats.setBrowser(browser);
        stats.setOperatingSystem(operatingSystem);

        statsRepository.save(stats);
    }
}
