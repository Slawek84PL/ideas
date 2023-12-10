package pl.slawek.ideas.domain.controler.view;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.slawek.ideas.domain.model.Category;
import pl.slawek.ideas.domain.service.CategoryService;

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
    public String edit(@PathVariable UUID id,
                       @Valid @ModelAttribute("category") Category category,
                       BindingResult bindingResult,
                       RedirectAttributes ra,
                       Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("category", category);
            model.addAttribute("message", "Błąd zapisu");
            return "admin/category/edit";
        }

        try {
            categoryService.updateCategory(id, category);
            ra.addFlashAttribute("message", "Kategoria zapisana");

        } catch (Exception e) {
            model.addAttribute("category", category);
            model.addAttribute("message", "Nieznany błąd zapisu");
            return "admin/category/edit";
        }

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
