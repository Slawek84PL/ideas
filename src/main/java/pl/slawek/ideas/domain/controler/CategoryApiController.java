package pl.slawek.ideas.domain.controler;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.slawek.ideas.domain.model.Category;
import pl.slawek.ideas.domain.service.CategoryService;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/categories")
class CategoryApiController {

    private final CategoryService categoryService;

    CategoryApiController(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    Page<Category> getCategories(Pageable pageable) {
        return categoryService.getCategories(pageable);
    }

    @GetMapping("{id}")
    Category getCategory(@PathVariable UUID id) {
        return categoryService.getCategory(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("{id}")
    Category updateCategory(@PathVariable UUID id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    void deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
    }

}
