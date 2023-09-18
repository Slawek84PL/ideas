package pl.slawek.ideas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/questions")
class QuestionController {

    private final QuestionService questionService;

    QuestionController(final QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    List<Question> getQuestions() {
        return questionService.getQuestions();
    }

    @GetMapping({"id"})
    Question getQuestion(@PathVariable UUID id) {
        return questionService.getQuestion(id);
    }

    @PostMapping
    Question createQuestion(@RequestBody Question question) {
        return questionService.createQuestion(question);
    }

    @PutMapping({"id"})
    Question updateQuestion(@PathVariable UUID id, @RequestBody Question question) {
        return questionService.updateQuestion(id, question);
    }

    @DeleteMapping({"id"})
    void deleteQuestion(@PathVariable UUID id) {
        questionService.deleteQuestion(id);
    }
}
