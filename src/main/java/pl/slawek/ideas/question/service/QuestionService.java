package pl.slawek.ideas.question.service;

import org.springframework.stereotype.Service;
import pl.slawek.ideas.question.domain.model.Question;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class QuestionService {

    public List<Question> getQuestions() {
        return Arrays.asList(
                new Question("Question1"),
                new Question("Question2"));
    }

    public Question getQuestion(final UUID id) {
        return new Question("Question " + id);
    }

    public Question createQuestion(Question question) {
        question.setId(UUID.randomUUID());
        return question;
    }

    public Question updateQuestion(final UUID id, final Question question) {
        return question;
    }

    public void deleteQuestion(final UUID id) {

    }
}
