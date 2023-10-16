package pl.slawek.ideas.question.controler.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.slawek.ideas.question.domain.model.Category;
import pl.slawek.ideas.question.service.CategoryService;

import java.util.UUID;

@Controller
@RequestMapping("admin/categories")
class CategoryAdminViewController {

    private final CategoryService categoryService;

    CategoryAdminViewController(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String indexView(Model model) {
        model.addAttribute("categories", categoryService.getCategories());

        return "admin/category/index";
    }

    @GetMapping("/add")
    public String addView(Model model) {
        model.addAttribute("category", new Category());

        return "admin/category/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("category") Category category) {
        categoryService.createCategory(category);

        return "redirect:/admin/categories";
    }

    @GetMapping("{id}")
    public String editView(Model model, @PathVariable UUID id) {
        model.addAttribute("category", categoryService.getCategory(id));

        return "admin/category/edit";
    }

    @PostMapping("{id}")
    public String edit(@ModelAttribute("category") Category category, @PathVariable UUID id) {
        categoryService.updateCategory(id, category);

        return "redirect:/admin/categories";
    }

    @GetMapping("{id}/delete")
    public String deleteView(Model model, @PathVariable UUID id) {
        model.addAttribute("category", categoryService.getCategory(id));

        return "admin/category/delete";
    }

    @PostMapping("{id}/delete")
    public String delete(@PathVariable UUID id) {
        categoryService.deleteCategory(id);

        return "redirect:/admin/categories";
    }
}
