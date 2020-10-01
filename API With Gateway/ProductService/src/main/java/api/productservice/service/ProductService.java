package api.productservice.service;

import api.productservice.domain.AllProducts;
import api.productservice.domain.Product;
import api.productservice.repository.ProductRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public AllProducts getAll () {
        AllProducts result = new AllProducts();
        result.setAllProducts(this.productRepository.findAll());
        return result;
    }

    public Product findById(long id) {
        return this.productRepository.findById(id).orElseThrow(IndexOutOfBoundsException::new);
    }

    public void delete(long id) {
        this.productRepository.deleteById(id);
    }

}
