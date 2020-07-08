package exam.security.domain.dto;


import exam.security.domain.entity.CategoryName;

public class CategoryDto extends BaseDto {

    private CategoryName name;
    private String description;

    public CategoryDto() {
    }

    public CategoryName getName() {
        return name;
    }

    public void setName(CategoryName name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
