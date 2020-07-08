package exam.security.service.impl;

import exam.security.domain.entity.Category;
import exam.security.domain.entity.CategoryName;
import exam.security.repository.CategoryRepository;
import exam.security.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initializeCategories() {

        if (this.categoryRepository.count() != 0) {
            return;
        }
        Arrays.stream(CategoryName.values())
                .forEach(value -> {
                    Category category = new Category(value);
                    category.setDescription(String.format("Description of %s",
                            value.name()));
                    this.categoryRepository.saveAndFlush(category);
                });
    }
}
