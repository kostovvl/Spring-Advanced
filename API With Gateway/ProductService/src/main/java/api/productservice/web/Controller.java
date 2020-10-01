package api.productservice.web;

import api.productservice.domain.Product;
import api.productservice.innerSecurity.ApiKey;
import api.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/products")
public class Controller {

    private final ProductService productService;
    private final ApiKey apiKey;

    @Autowired
    public Controller(ProductService productService, ApiKey apiKey) {
        this.productService = productService;
        this.apiKey = apiKey;
    }

    @PostMapping("/create/{apiKey}")
    public ResponseEntity<?> createProduct(@PathVariable(name = "apiKey") String apiKey,
                                           @RequestBody()Product product ){

        if (this.apiKey.getSecurityKey().equals(apiKey)) {
            try {
                Product result = this.productService.addNew(product);
                return new ResponseEntity<>(result, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

        } else {
         return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    @GetMapping("/all/{apiKey}")
    public ResponseEntity<?> getAllProducts() {

    }

    @GetMapping("/details/{productId}/{apiKey}")
    public ResponseEntity<?> getByProductId() {

    }

    @DeleteMapping("/delete/{productID}/{apiKey}")
    public ResponseEntity<?> deleteById() {

    }

}
