package spring.security.web;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.security.domain.binding.UserRegisterBinding;
import spring.security.domain.dto.UserEntityDto;
import spring.security.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userEntityService;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userEntityService, ModelMapper mapper, PasswordEncoder passwordEncoder) {
        this.userEntityService = userEntityService;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
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

        //TODO crypt passwords of userRegisterBinding

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("userRegister", userRegisterBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegister", bindingResult);

            return "redirect:/users/register";
        }

        if (!userRegisterBinding.getPassword().equals(userRegisterBinding.getPasswordConfirm())) {
            redirectAttributes.addFlashAttribute("userRegister", userRegisterBinding);
            redirectAttributes.addFlashAttribute("passwordsNoMatch", true);

            return "redirect:/users/register";
        }


        if (this.userEntityService.userExists(userRegisterBinding.getUsername())) {
            redirectAttributes.addFlashAttribute("userRegister", userRegisterBinding);
            redirectAttributes.addFlashAttribute("userExists", true);

            return "redirect:/users/register";
        }

        UserEntityDto userEntityDto = this.mapper.map(userRegisterBinding, UserEntityDto.class);
        userEntityDto.setPassword(passwordEncoder.encode(userRegisterBinding.getPassword()));

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}