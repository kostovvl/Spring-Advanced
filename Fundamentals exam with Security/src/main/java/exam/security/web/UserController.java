package exam.security.web;

import exam.security.domain.binding.UserRegisterBinding;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/register")
    public String register(Model model) {
        if (model.getAttribute("userRegister") == null) {
            model.addAttribute("userRegister", new UserRegisterBinding());
        }

        return "register";
    }

    @GetMapping("/login")
    public String login(Model model) {

        if (model.getAttribute("userLogin") == null) {
            model.addAttribute("userLogin", new UserRegisterBinding());
        }


        return "login";
    }

}
