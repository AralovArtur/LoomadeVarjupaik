package ee.ut.loomadevp.uudiskirjad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UudiskirjaController {
    private final UudiskirjaRepository uudiskirjaRepository;
    private final SimpMessageSendingOperations messagingTemplate;

    @Autowired
    public UudiskirjaController(UudiskirjaRepository uudiskirjaRepository, SimpMessageSendingOperations messagingTemplate) {
        this.uudiskirjaRepository = uudiskirjaRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping(path = "/")
    public String getMainPage(Model model) {
        // take all subscriptions from database
        List<Uudiskiri> subscriptionList = uudiskirjaRepository.findAll();

        // fill template model with all subscriptions
        model.addAttribute("uudiskirjad", subscriptionList);

        return "index";
    }

    @PostMapping(path = "/")
    public String sendSubscription(@ModelAttribute UudiskirjaDTO subscriptionDTO) {
        // map DTO to entity
        Uudiskiri entity = mapDtoToEntity(subscriptionDTO);

        // save new entity
        uudiskirjaRepository.save(entity);

        messagingTemplate.convertAndSend("/topic/subscriptions", subscriptionDTO);

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
