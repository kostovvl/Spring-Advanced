package exam.security.service;

import exam.security.domain.dto.CategoryDto;
import exam.security.domain.entity.CategoryName;

public interface CategoryService {

     void initializeCategories();

     CategoryDto findByName(CategoryName name);

}
