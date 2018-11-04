package ee.ut.loomadevp.controller;

import ee.ut.loomadevp.model.Animal;
import ee.ut.loomadevp.model.AnimalDTO;
import ee.ut.loomadevp.repository.AnimalRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;

@Controller
public class VarjupaikController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final AnimalRepository animalRepository;

    public VarjupaikController(AnimalRepository animalRepository) throws Exception {
        this.animalRepository = animalRepository;
    }

    @RequestMapping(path = "/loomadvp", method = RequestMethod.GET)
    public String showAllLoomad(Model model) {
        model.addAttribute("loomad", animalRepository.findAll());
        return "loomad_vp";
    }

    @RequestMapping(path = "/viiols", method = RequestMethod.POST)
    public String reg(@ModelAttribute AnimalDTO animalDTO, Model model) throws Exception {
        final String sql = "INSERT INTO Loomad (liik, vanus, linn, pilt) VALUES (?, ?, ?, ?)";

        String liik = animalDTO.getLiik();
        model.addAttribute("liik", liik);
        int vanus = animalDTO.getVanus();
        model.addAttribute("vanus", vanus);
        String linn = animalDTO.getLinn();
        model.addAttribute("linn", linn);
        String pilt = getImageBase64Format(animalDTO.getAvatarImage());

        jdbcTemplate.update(sql, new Object[]{liik, vanus, linn, pilt});
        return "vii_ols";
    }

    private String getImageBase64Format(MultipartFile file) throws Exception{
        return "data:" + file.getContentType() + ";base64," + Base64.encodeBase64String(file.getBytes());
    }

    @RequestMapping(path = "/viiols", method = RequestMethod.GET)
    public String viiOLS() {
        return "vii_ols";
    }

    @RequestMapping(path = "/kontakt", method = RequestMethod.GET)
    public String kontakt() {
        return "kontakt";
    }

    @GetMapping(path = "/smart-id/login")
    public String getSmartIdLoginPage() {
        return "smartid/login";
    }

    @GetMapping(path = "/secured")
    public String getSecuredPage() {
        return "secured/secured";
    }
}