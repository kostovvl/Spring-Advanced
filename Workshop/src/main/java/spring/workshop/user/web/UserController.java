package spring.workshop.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/register")
    public String register() {
        return null;
    }


    @PostMapping("/register")
    public String registerConfirm() {
        return null;
    }


}
