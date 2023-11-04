package pl.slawek.ideas.domain.controler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.slawek.ideas.domain.model.Answer;
import pl.slawek.ideas.domain.service.AnswerService;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Answer createAnswer(@PathVariable("question-id") UUID questionId, @RequestBody Answer answer) {
        return answerService.createAnswer(questionId, answer);
    }

    @PutMapping("{answer-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Answer updateAnswer(
            @PathVariable("question-id") UUID questionId, @PathVariable("answer-id") UUID answerId,
            @RequestBody Answer answer) {
        return answerService.updateAnswer(answerId, answer);
    }

    @DeleteMapping("{answer-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteAnswer(@PathVariable("answer-id") UUID answerId) {
        answerService.deleteAnswer(answerId);
    }
}
