package pl.slawek.ideas.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.slawek.ideas.domain.model.Question;
import pl.slawek.ideas.domain.repository.QuestionRepository;

import java.util.List;
import java.util.UUID;

@Service
public class QuestionService {

    private final QuestionRepository repository;

    public QuestionService(QuestionRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Question> getQuestions() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Question getQuestion(final UUID id) {
        return repository.getReferenceById(id);
    }

    @Transactional
    public Question createQuestion(Question questionRequest) {
        Question question = new Question();

        question.setName(questionRequest.getName());

        return repository.save(question);
    }

    @Transactional
    public Question updateQuestion(final UUID id, final Question questionRequest) {
        Question question = repository.getReferenceById(id);

        question.setName(questionRequest.getName());

        return repository.save(question);
    }

    @Transactional
    public void deleteQuestion(final UUID id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Question> findAllByCategoryId(UUID id) {
        return repository.findAllByCategoryId(id);
    }
}
