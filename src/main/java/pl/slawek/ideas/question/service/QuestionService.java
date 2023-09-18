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
        return null;
    }

    public Question createQuestion(final Question question) {
        return null;
    }

    public Question updateQuestion(final UUID id, final Question question) {
        return null;
    }

    public void deleteQuestion(final UUID id) {

    }
}
