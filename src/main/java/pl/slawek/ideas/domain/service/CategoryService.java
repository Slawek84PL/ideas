package pl.slawek.ideas.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import pl.slawek.ideas.domain.model.Category;
import pl.slawek.ideas.domain.repository.CategoryRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<Category> getCategories(Pageable pageable) {
        return repository.findAll(pageable);
    }


    @Transactional(readOnly = true)
    public Category getCategory(final UUID id) {
        return repository.getReferenceById(id);
    }

    @Transactional
    public Category createCategory(final Category categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());

        return repository.save(category);
    }

    @Transactional
    public Category updateCategory(final UUID id, final Category categoryRequest) {
        Category category = repository.getReferenceById(id);
        category.setName(categoryRequest.getName());
        return repository.save(category);
    }

    @Transactional
    public void deleteCategory(final UUID id) {
        repository.deleteById(id);
    }

}
