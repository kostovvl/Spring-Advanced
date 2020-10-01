package api.gateway.web.client;

import api.gateway.config.Global;
import api.gateway.domain.AllProducts;
import api.gateway.domain.Product;
import api.gateway.innerSecurity.ApiKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductClient {

    private final ApiKey apiKey;
    private final RestTemplate restTemplate;

    @Autowired()
    public ProductClient(ApiKey apiKey, RestTemplate restTemplate) {
        this.apiKey = apiKey;
        this.restTemplate = restTemplate;
    }

    public Product createProduct(Product product) {
        return this.restTemplate.postForObject(Global.PRODUCT_SERVICE_URL + "create/" + this.apiKey.getSecurityKey(),
                product, Product.class);
    }

    public List<Product> getAllProducts() {
        return this.restTemplate.getForObject(Global.PRODUCT_SERVICE_URL + "all/" + this.apiKey.getSecurityKey(),
                AllProducts.class).getAllProducts();
    }

    public Product getProductDetails(long id) {
        return this.restTemplate.getForObject(Global.PRODUCT_SERVICE_URL + "details/" + id + "/" + this.apiKey.getSecurityKey(),
                Product.class);
    }

    public boolean deleteProduct(long id) {
        try {
        this.restTemplate.delete(Global.PRODUCT_SERVICE_URL + "delete/" + id + "/" + this.apiKey.getSecurityKey());
        return true;
        } catch (Exception e) {
            return false;
        }
    }
}
