package pl.slawek.ideas.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.slawek.ideas.domain.model.Category;
import pl.slawek.ideas.domain.repository.CategoryRepository;

import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Page<Category> getCategories(Pageable pageable) {
        return getCategories(null, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Category> getCategories(String search, Pageable pageable) {
        if(search == null) {
            return repository.findAll(pageable);
        } else {
            return repository.findByNameContainingIgnoreCase(search, pageable);
        }
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
