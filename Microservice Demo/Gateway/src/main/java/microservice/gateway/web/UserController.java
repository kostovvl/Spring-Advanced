package microservice.gateway.web;

import microservice.gateway.domain.binding.UserRegisterBinding;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final RestTemplate restTemplate;
    private final WebClient.Builder webClientBuilder;

    public UserController(RestTemplate restTemplate, WebClient.Builder webClientBuilder) {
        this.restTemplate = restTemplate;
        this.webClientBuilder = webClientBuilder;
    }

    @GetMapping("/register")
    public String register(Model model) {

        if (model.getAttribute("userRegister") == null) {
            model.addAttribute("userRegister", new UserRegisterBinding());
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute("userRegister")
                                          UserRegisterBinding userRegisterBinding, BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("userRegister", userRegisterBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegister", bindingResult);

            return "redirect:/users/register";
        }

        if (!userRegisterBinding.getPassword().equals(userRegisterBinding.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegister", userRegisterBinding);
            redirectAttributes.addFlashAttribute("passwordsNoMatch", true);

            return "redirect:/users/register";
        }


        return "redirect:/users/login";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/all")
    public List<UserRegisterBinding> all() {

        List<UserRegisterBinding> registerBindings = new ArrayList<>();
        for (int i = 0; i < 10 ; i++) {
            String variable = String.valueOf(i + 1);
            registerBindings.add(restTemplate.getForObject("http://localhost:8081/users/get" + variable,
                    UserRegisterBinding.class));
        }

        return registerBindings;
    }

}
