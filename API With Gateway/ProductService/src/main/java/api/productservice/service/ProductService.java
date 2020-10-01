package api.productservice.service;

import api.productservice.domain.Product;
import api.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addNew (Product product) {
        return this.productRepository.saveAndFlush(product);
    }

}
