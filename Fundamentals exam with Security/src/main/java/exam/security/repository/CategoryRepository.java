package exam.security.repository;

import exam.security.domain.entity.Category;
import exam.security.domain.entity.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Category findByName(CategoryName name);

}
