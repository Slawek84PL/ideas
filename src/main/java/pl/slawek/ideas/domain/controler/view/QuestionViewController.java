package pl.slawek.ideas.domain.controler.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.slawek.ideas.domain.model.Question;
import pl.slawek.ideas.domain.service.AnswerService;
import pl.slawek.ideas.domain.service.CategoryService;
import pl.slawek.ideas.domain.service.QuestionService;

import java.util.UUID;

@Controller
@RequestMapping("/questions")
class QuestionViewController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final CategoryService categoryService;

    QuestionViewController(
            final QuestionService questionService,
            final AnswerService answerService,
            final CategoryService categoryService) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String indexView(Model model) {
        model.addAttribute("questions", questionService.getQuestions());
        model.addAttribute("categories", categoryService.getCategories());

        return "question/index";
    }

    @GetMapping("{id}")
    public String singleView(Model model, @PathVariable UUID id) {
        model.addAttribute("question", questionService.getQuestion(id));
        model.addAttribute("answers", answerService.getAnswers(id));
        model.addAttribute("categories", categoryService.getCategories());

        return "question/single";
    }

    @GetMapping("/add")
    public String addView(Model model) {
        model.addAttribute("question", new Question());

        return "question/add";
    }

    @PostMapping
    public String add(Question question) {
        questionService.createQuestion(question);

        return "redirect:/questions";
    }

}
