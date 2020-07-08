package exam.security.domain.entity;

import javax.persistence.*;
import java.util.Locale;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    private CategoryName name;
    private String description;

    public Category() {
    }

    public Category(CategoryName name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    public CategoryName getName() {
        return name;
    }

    public void setName(CategoryName name) {
        this.name = name;
    }

    @Column(name = "description", columnDefinition = "text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
