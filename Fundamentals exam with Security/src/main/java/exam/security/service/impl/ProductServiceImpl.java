package exam.security.service.impl;

import exam.security.domain.dto.ProductDto;
import exam.security.domain.entity.Product;
import exam.security.domain.view.ProductView;
import exam.security.repository.ProductRepository;
import exam.security.service.CategoryService;
import exam.security.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper mapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ModelMapper mapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.mapper = mapper;
    }

    @Override
    public void addProduct(ProductDto productDto) {
        productDto.setCategory(this.categoryService.findByName(productDto.getCategory().getName()));
        this.productRepository.saveAndFlush(this.mapper.map(productDto, Product.class));
    }

    @Override
    public boolean productExists(String name) {
        return this.productRepository.findByName(name).orElse(null) != null;
    }

    @Override
    public List<ProductView> findAllProducts() {

        return this.productRepository.findAll().stream()
                .map(p -> this.mapper.map(p, ProductView.class))
                .collect(Collectors.toList());
    }


    @Transactional
    @Override
    public void deleteById(String id) {
        this.productRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAll() {
        this.productRepository.deleteAll();
    }

    @Override
    public BigDecimal getTotalPrice() {
        BigDecimal result = new BigDecimal("0");
        List<Product> products = this.productRepository.findAll();

        for (Product product : products) {
            result = result.add(product.getPrice());
        }
        return result;
    }
}
