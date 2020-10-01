package api.gateway.domain;

import java.util.List;

public class AllProducts {

    public List<Product> allProducts;

    public AllProducts() {
    }

    public List<Product> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(List<Product> allProducts) {
        this.allProducts = allProducts;
    }
}
