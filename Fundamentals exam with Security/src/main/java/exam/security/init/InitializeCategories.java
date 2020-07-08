package exam.security.init;

import exam.security.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitializeCategories implements CommandLineRunner {

    private final CategoryService categoryService;

    public InitializeCategories(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.categoryService.initializeCategories();
    }
}
