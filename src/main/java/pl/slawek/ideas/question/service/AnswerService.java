package pl.slawek.ideas.question.service;

import org.springframework.stereotype.Service;
import pl.slawek.ideas.question.domain.model.Answer;
import pl.slawek.ideas.question.domain.model.Question;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class AnswerService {
    public List<Answer> getAnswers(final UUID questionId) {
        return Arrays.asList(
                new Answer("Answer 1"),
                new Answer("Answer 2"),
                new Answer("Answer 3"));
    }

    public Answer getAnswer(final UUID id) {
        return new Answer("Answer " + id);
    }

    public Answer createAnswer(final UUID questionId, final Answer answer) {
        return new Answer("Answer");
    }

    public Question updateAnswer(final UUID id, final Question question) {
        return null;
    }

    public void deleteAnswer(final UUID id) {

    }
}
