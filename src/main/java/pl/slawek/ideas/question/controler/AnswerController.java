package pl.slawek.ideas.question.controler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.slawek.ideas.question.domain.model.Answer;
import pl.slawek.ideas.question.domain.model.Question;
import pl.slawek.ideas.question.service.AnswerService;
import pl.slawek.ideas.question.service.QuestionService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/questions/{question-id}/answers")
class AnswerController {

    private final AnswerService answerService;

    AnswerController(final AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping
    List<Answer> getAnswers(@PathVariable("question-id") UUID questionId) {
        return answerService.getAnswers(questionId);
    }

    @GetMapping("{answer-id}")
    Answer getAnswer(@PathVariable("question-id") UUID questionId, @PathVariable("answer-id") UUID answerId) {
        return answerService.getAnswer(answerId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Answer createQuestion(@PathVariable("question-id") UUID questionId, @RequestBody Answer answer) {
        return answerService.createAnswer(questionId, answer);
    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("{answer-id}")
    Question updateAnswer(
            @PathVariable("question-id") UUID questionId,
            @PathVariable("answer-id") UUID answerId,
            @RequestBody Question question) {
        return answerService.updateAnswer(answerId, question);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{answer-id}")
    void deleteAnswer(@PathVariable("answer-id") UUID answerId) {
        answerService.deleteAnswer(answerId);
    }
}
