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
@RequestMapping("/search")
class SearchViewController extends IdeasCommonViewController {

    private final QuestionService questionService;

    @GetMapping
    public String seatchView(
            @RequestParam(name = "query", required = false) String query,
            @RequestParam(name = "page", defaultValue = "1") int page,
            Model model) {

        PageRequest pageRequest = getPageRequest(page);

        if (query != null) {
            Page<Question> questionPage = questionService.fingByQuery(query, pageRequest);
            model.addAttribute("questionsPage", questionPage);
            model.addAttribute("query", query);
            paging(model, questionPage);
        }

        addGlobalAttribute(model);

        return "search/index";
    }

}
