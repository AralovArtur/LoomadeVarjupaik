package ee.ut.loomadevp.controller;

import ee.ut.loomadevp.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class VarjupaikController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final AnimalRepository animalRepository;

    public VarjupaikController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @RequestMapping(path = "/loomadvp", method = RequestMethod.GET)
    public String showAllLoomad(Model model) {
        model.addAttribute("loomad", animalRepository.findAll());
        return "loomad_vp";
    }

    @RequestMapping(path = "/viiols", method = RequestMethod.POST)
    public String reg(HttpServletRequest request, Model model) {
        final String sql = "INSERT INTO Loomad (liik, vanus, linn) VALUES (?, ?, ?)";

        String liik = request.getParameter("liik");
        model.addAttribute("liik", liik);
        int vanus = Integer.parseInt(request.getParameter("vanus"));
        model.addAttribute("vanus", vanus);
        String linn = request.getParameter("linn");
        model.addAttribute("linn", linn);
        jdbcTemplate.update(sql, new Object[]{liik, vanus, linn});

        return "loomad_vp";
    }

    @RequestMapping(path = "/viiols", method = RequestMethod.GET)
    public String viiOLS() {
        return "vii_ols";
    }

    @RequestMapping(path = "/kontakt", method = RequestMethod.GET)
    public String kontakt() {
        return "kontakt";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(path = "/smart-id/login", method = RequestMethod.GET)
    public String smartID() {
        return "smartid/login";
    }
}