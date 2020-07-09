package exam.security.web;

import exam.security.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }


//    @GetMapping("/")
//    public String index() {
//        return "index";
//    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {

        if (principal.getName() != null) {
        model.addAttribute("products", this.productService.findAllProducts());
        model.addAttribute("price", this.productService.getTotalPrice());
        return "home";
        } else {
            return "index";
        }
    }

    @GetMapping("/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }

    @GetMapping("/login")
    public String loginRedirect() {
        return "redirect:/users/login";
    }

}
