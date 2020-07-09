package exam.security.service;

import exam.security.domain.dto.ProductDto;
import exam.security.domain.view.ProductView;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    void addProduct(ProductDto productDto);

    boolean productExists(String name);

    List<ProductView> findAllProducts();

    void deleteById(String id);

    void deleteAll();

    BigDecimal getTotalPrice();
}
