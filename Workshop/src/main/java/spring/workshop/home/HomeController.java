package spring.workshop.home;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.exceptions.TemplateInputException;
import spring.workshop.config.TabulaException;

@Controller
public class HomeController {

    @GetMapping("/")
    public String login() {

        return "login/login";
    }


    @GetMapping("/home")
    public String postHome() {
        return "home/home";
    }

    @PostMapping("/")
    public String loginFail(@RequestParam("error") String error) {

        return "login/login";

    }


}
