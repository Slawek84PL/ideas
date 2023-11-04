package pl.slawek.ideas.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.slawek.ideas.domain.model.Answer;
import pl.slawek.ideas.domain.model.Question;
import pl.slawek.ideas.domain.repository.AnswerRepository;
import pl.slawek.ideas.domain.repository.QuestionRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AnswerService {

    private final AnswerRepository repository;

    private final QuestionRepository questionRepository;

    public AnswerService(AnswerRepository repository, QuestionRepository questionRepository) {
        this.repository = repository;
        this.questionRepository = questionRepository;
    }

    @Transactional(readOnly = true)
    public List<Answer> getAnswers(final UUID questionId) {
        return repository.findAllByQuestionId(questionId);
    }

    @Transactional(readOnly = true)
    public Answer getAnswer(final UUID id) {
        return repository.getReferenceById(id);
    }

    @Transactional
    public Answer createAnswer(final UUID questionId, final Answer answerRequest) {
        Answer answer = new Answer();

        answer.setName(answerRequest.getName());

        Question question = questionRepository.getReferenceById(questionId);
        question.addAnswer(answer);

        repository.save(answer);
        questionRepository.save(question);

        return answer;
    }

    @Transactional
    public Answer updateAnswer(final UUID answerId, Answer answerRequest) {
        Answer answer = repository.getReferenceById(answerId);
        answer.setName(answerRequest.getName());

        return repository.save(answer);
    }

    public void deleteAnswer(final UUID answerId) {
        repository.deleteById(answerId);
    }
}
