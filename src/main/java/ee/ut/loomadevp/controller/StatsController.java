package ee.ut.loomadevp.controller;

import ee.ut.loomadevp.repository.StatsRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatsController {
    private final StatsRepository statsRepository;

    public StatsController(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    @RequestMapping(path = "/stats", method = RequestMethod.GET)
    public String showAllStats(Model model) {
        model.addAttribute("stats", statsRepository.findAll());
        return "stats/stats";
    }
}
