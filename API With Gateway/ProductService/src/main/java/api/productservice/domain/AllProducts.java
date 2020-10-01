package api.productservice.domain;

import java.util.List;

public class AllProducts {

    List<Product> allProducts;

    public AllProducts() {
    }

    public List<Product> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(List<Product> allProducts) {
        this.allProducts = allProducts;
    }
}
