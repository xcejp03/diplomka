package cz.vse.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by pcejk on 20.12.2016.
 */
@Controller
public class LoginController {

    // Login form
    @RequestMapping("/login")
    public String login() {

//        String password = "heslo";
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        System.out.println(" HESLO XXX: " + passwordEncoder.encode(password));

        return "login";
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login-error";
    }

}