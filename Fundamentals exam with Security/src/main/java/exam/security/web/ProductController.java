package exam.security.web;

import exam.security.domain.binding.ProductAddBinding;
import exam.security.domain.dto.ProductDto;
import exam.security.domain.entity.CategoryName;
import exam.security.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper mapper;

    public ProductController(ProductService productService, ModelMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }

    @GetMapping("/add")
    public String add(Model model) {

        if (model.getAttribute("productAdd") == null) {
            model.addAttribute("productAdd", new ProductAddBinding());
        }
        model.addAttribute("categories", CategoryName.values());

        return "product-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("productAdd")
                                     ProductAddBinding productAddBinding, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAdd", productAddBinding);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAdd", bindingResult);
            return "redirect:/products/add";
        }


        if (this.productService.productExists(productAddBinding.getName())) {
            redirectAttributes.addFlashAttribute("productAdd", productAddBinding);
            redirectAttributes.addFlashAttribute("productExists", true);
            return "redirect:/products/add";
        }

        this.productService.addProduct(this.mapper.map(productAddBinding, ProductDto.class));

        return "redirect:/home";
    }

    @GetMapping("/buy")
    public String buy(@RequestParam("id") String id) {
        this.productService.deleteById(id);
        return "redirect:/home";
    }

    @GetMapping("/buy/all")
    public String buyAll() {
        this.productService.deleteAll();
        return "redirect:/home";
    }

}
