package ee.ut.loomadevp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ee.ut.loomadevp.model.*;
import ee.ut.loomadevp.repository.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRespository roleRespository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        sendSimpleEmail(user.getEmail());
        Role userRole = roleRespository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Autowired
    public JavaMailSender emailSender;

    @ResponseBody
    @RequestMapping("/sendSimpleEmail")
    public void sendSimpleEmail(String userEmail) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(userEmail);
        message.setSubject("Registration");
        message.setText("Hello, thank you for registering on our site.");
        this.emailSender.send(message);
    }
}