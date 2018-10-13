package ee.ut.loomadevp.smartid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SmartIDController {
    @RequestMapping(path = "/smart-id/login", method = RequestMethod.GET)
    public String smartID() {
        return "smartid/login";
    }
}
