package api.productservice.web;

import api.productservice.domain.AllProducts;
import api.productservice.domain.Product;
import api.productservice.innerSecurity.ApiKey;
import api.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController()
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
            try {
                checkKey(apiKey);
                Product result = this.productService.addNew(product);
                return new ResponseEntity<>(result, HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

    }

    @GetMapping("/all/{apiKey}")
    public ResponseEntity<?> getAllProducts(@PathVariable(name = "apiKey") String apiKey) {
        try {
            checkKey(apiKey);
           AllProducts result = this.productService.getAll();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/details/{productId}/{apiKey}")
    public ResponseEntity<?> getByProductId(@PathVariable(name = "productId") long productId,
                                            @PathVariable(name = "apiKey") String apiKey) {

        try {
            checkKey(apiKey);
            Product result = this.productService.findById(productId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/delete/{productId}/{apiKey}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "productId") long productId,
                                        @PathVariable(name = "apiKey") String apiKey) {

        try {
            checkKey(apiKey);
           this.productService.delete(productId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private void checkKey(String apiKey) {
        if (!this.apiKey.getSecurityKey().equals(apiKey)) {
            throw new IllegalArgumentException("Unauthorized");
        }
    }

}
