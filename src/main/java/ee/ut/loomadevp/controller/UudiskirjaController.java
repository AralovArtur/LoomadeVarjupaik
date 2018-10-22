package ee.ut.loomadevp.controller;

import ee.ut.loomadevp.repository.StatsRepository;
import ee.ut.loomadevp.repository.UudiskirjaRepository;
import ee.ut.loomadevp.service.StatsService;
import ee.ut.loomadevp.model.Uudiskiri;
import ee.ut.loomadevp.model.UudiskirjaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UudiskirjaController {
    private final UudiskirjaRepository uudiskirjaRepository;
    private final SimpMessageSendingOperations messagingTemplate;
    private final StatsService statsService;
    private final StatsRepository statsRepository;

    @Autowired
    public UudiskirjaController(UudiskirjaRepository uudiskirjaRepository, SimpMessageSendingOperations messagingTemplate, StatsService statsService, StatsRepository statsRepository) {
        this.uudiskirjaRepository = uudiskirjaRepository;
        this.messagingTemplate = messagingTemplate;
        this.statsService = statsService;
        this.statsRepository = statsRepository;
    }

    @GetMapping(path = "/")
    public String getMainPage(Model model, HttpServletRequest request) {
        // take all subscriptions from database
        List<Uudiskiri> subscriptionList = uudiskirjaRepository.findAll();
        statsService.saveUserAgent(request);

        // fill template model with all subscriptions
        model.addAttribute("uudiskirjad", subscriptionList);

        return "index";
    }

    @PostMapping(path = "/")
    public String sendSubscription(@ModelAttribute UudiskirjaDTO subscriptionDTO, Model model) {
        // map DTO to entity
        Uudiskiri entity = mapDtoToEntity(subscriptionDTO);

        // save new entity
        uudiskirjaRepository.save(entity);

        messagingTemplate.convertAndSend("/topic/uudiskiri", subscriptionDTO);

        // redirect to home page
        return "redirect:/";
    }

    private Uudiskiri mapDtoToEntity(UudiskirjaDTO subscriptionDTO) {
        Uudiskiri subscriptionEntity = new Uudiskiri();
        subscriptionEntity.setEmail(subscriptionDTO.getEmail());
        subscriptionEntity.setSonum(subscriptionDTO.getSonum());
        return subscriptionEntity;
    }
}
