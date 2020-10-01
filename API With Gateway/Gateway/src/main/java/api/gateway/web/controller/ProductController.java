package api.gateway.web.controller;

import api.gateway.domain.Product;
import api.gateway.web.client.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/products")
public class ProductController {

    private final ProductClient productClient;

    @Autowired
    public ProductController(ProductClient productClient) {
        this.productClient = productClient;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody() Product product) {
        return new ResponseEntity<>(this.productClient.createProduct(product), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> allProducts() {
        return new ResponseEntity<>(this.productClient.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/details/{productId}")
    public ResponseEntity<?> getProductDetails(@PathVariable(name = "productId") long productId) {
        return new ResponseEntity<>(this.productClient.getProductDetails(productId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "productId") long productId) {
        return new ResponseEntity<>(this.productClient.deleteProduct(productId), HttpStatus.OK);
    }

}


