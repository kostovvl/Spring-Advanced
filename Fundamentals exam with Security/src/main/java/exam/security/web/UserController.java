package exam.security.web;

import exam.security.domain.binding.UserRegisterBinding;
import exam.security.domain.dto.UserDto;
import exam.security.service.UserService;
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

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, ModelMapper mapper, PasswordEncoder passwordEncoder) {
        this.userService = userService;
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
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegister", userRegisterBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegister", bindingResult);
            return "redirect:/users/register";
        }

        if (!userRegisterBinding.getPassword().equals(userRegisterBinding.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegister", userRegisterBinding);
            redirectAttributes.addFlashAttribute("wrongPassword", true);
            return "redirect:/users/register";
        }

        UserDto userDto = this.userService.findByUsername(userRegisterBinding.getUsername());

        if (userDto != null) {
            redirectAttributes.addFlashAttribute("userRegister", userRegisterBinding);
            redirectAttributes.addFlashAttribute("userExists", true);
            return "redirect:/users/register";
        }

        this.userService.recordNewUser(this.mapper.map(userRegisterBinding, UserDto.class));

        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String login(Model model, Principal principal) {

        if (model.getAttribute("userLogin") == null) {
            model.addAttribute("userLogin", new UserRegisterBinding());
        }

        model.addAttribute("user", principal);

        return "login/login";
    }

    @GetMapping("/login/fail")
    public String loginFail() {
        return "login/login-fail";
    }


}
