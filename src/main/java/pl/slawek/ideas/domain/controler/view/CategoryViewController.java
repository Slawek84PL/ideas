package pl.slawek.ideas.domain.controler.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.slawek.ideas.domain.model.Category;
import pl.slawek.ideas.domain.model.Question;
import pl.slawek.ideas.domain.service.CategoryService;
import pl.slawek.ideas.domain.service.QuestionService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/categories")
public class CategoryViewController {

    private final CategoryService categoryService;
    private final QuestionService questionService;

    public CategoryViewController(CategoryService categoryService, QuestionService questionService) {
        this.categoryService = categoryService;
        this.questionService = questionService;
    }

    @GetMapping("{id}")
    public String singleView(Model model, @PathVariable UUID id) {
        Category category = categoryService.getCategory(id);
        List<Question> questions = questionService.findAllByCategoryId(id);

        model.addAttribute("category", category);
        model.addAttribute("questions", questions);

        return "category/single";
    }
}
