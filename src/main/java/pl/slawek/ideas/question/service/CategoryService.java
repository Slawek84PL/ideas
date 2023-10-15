package pl.slawek.ideas.question.service;

import org.springframework.stereotype.Service;
import pl.slawek.ideas.question.domain.model.Category;
import pl.slawek.ideas.question.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final List<Category> categoryList = List.of(
            new Category("Category 1"),
            new Category("Category 2"),
            new Category("Category 3"));

    public List<Category> getCategories() {
        return categoryList;
    }

    public Category createCategory(final Category category) {
        Category newCategory = new Category(category.getName());
        categoryList.add(newCategory);
        return newCategory;
    }

    public Category updateCategory(final UUID id, final Category category) {
        Category oldCategory = categoryService(id);
        oldCategory.setName(category.getName());
        return oldCategory;
    }

    public void deleteCategory(final UUID id) {
        categoryList.remove(categoryService(id));
    }

    public Category getCategory(final UUID id) {
        return categoryService(id);
    }

    private Category categoryService(final UUID id) {
        for (Category category : categoryList) {
            if (category.getId().equals(id)) {
                return category;
            }
        }
        throw new NotFoundException("category", id);
    }
}
