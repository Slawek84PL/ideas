package pl.slawek.ideas.domain.controler.view;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.slawek.ideas.domain.common.IdeasCommonViewController;
import pl.slawek.ideas.domain.model.Question;
import pl.slawek.ideas.domain.service.AnswerService;
import pl.slawek.ideas.domain.service.QuestionService;

import java.util.UUID;

import static pl.slawek.ideas.domain.common.ControllerUtils.paging;

@RequiredArgsConstructor
@Controller
@RequestMapping("/questions")
class QuestionViewController extends IdeasCommonViewController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @GetMapping
    public String indexView(Model model) {
        model.addAttribute("questions", questionService.getQuestions());
        addGlobalAttribute(model);

        return "index/index";
    }

    @GetMapping("{id}")
    public String singleView(Model model, @PathVariable UUID id) {
        model.addAttribute("question", questionService.getQuestion(id));
        model.addAttribute("answers", answerService.getAnswers(id));
        addGlobalAttribute(model);

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

    @GetMapping("hot")
    public String hotView(
            @RequestParam(name = "page", defaultValue = "1") int page,
            Model model) {

        PageRequest pageRequest = getPageRequest(page);

        Page<Question> questionPage = questionService.findHot(pageRequest);

        model.addAttribute("questionsPage", questionPage);

        paging(model, questionPage);

        addGlobalAttribute(model);

        return "question/index";
    }

    @GetMapping("unanswered")
    public String unansweredView(
            @RequestParam(name = "page", defaultValue = "1") int page,
            Model model) {

        PageRequest pageRequest = getPageRequest(page);

        Page<Question> questionPage = questionService.findUnanswered(pageRequest);

        model.addAttribute("questionsPage", questionPage);

        paging(model, questionPage);

        addGlobalAttribute(model);

        return "question/index";
    }

}
