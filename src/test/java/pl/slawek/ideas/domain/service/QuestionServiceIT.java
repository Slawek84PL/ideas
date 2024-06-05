package pl.slawek.ideas.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.slawek.ideas.domain.model.Answer;
import pl.slawek.ideas.domain.model.Category;
import pl.slawek.ideas.domain.model.Question;
import pl.slawek.ideas.domain.repository.AnswerRepository;
import pl.slawek.ideas.domain.repository.QuestionRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
class QuestionServiceIT {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AnswerRepository answerRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldGetAllQuestions() {
        // given
        questionRepository.deleteAll();

        questionRepository.saveAll(List.of(
                new Question("Question1"),
                new Question("Question2"),
                new Question("Question3")
        ));

        // when
        List<Question> questions = questionService.getQuestions();

        //then
        assertThat(questions)
                .hasSize(3)
                .extracting(Question::getName)
                .containsExactly("Question1", "Question2", "Question3");
    }

    @Test
    void shouldGetSingleQuestion() {
        // given
        Question question = new Question("Question1");

        questionRepository.saveAll(List.of(
                new Question("Question2"),
                question,
                new Question("Question3")));

        // when
        Question result = questionService.getQuestion(question.getId());

        // then
        assertThat(result.getId()).isEqualTo(question.getId());
    }

    @Test
    void shouldCreateQuestion() {
        // given
        Question question = new Question("Question1");

        // when
        Question result = questionService.createQuestion(question);
        Question questionFromDB = questionRepository.findById(question.getId()).get();

        // then
        assertThat(question.getName()).isEqualTo(result.getName());
        assertThat(result.getName()).isEqualTo(questionFromDB.getName());
    }

    @Test
    void shouldUpdateQuestion() {
        // given
        Question question = new Question("Question1");
        question = questionService.createQuestion(question);

        question.setName("Updated Question");

        // when
        Question result = questionService.updateQuestion(question.getId(), question);

        // then
        assertThat(result.getName()).isSameAs(question.getName());
    }

    @Test
    void shouldDeleteQuestion() {
        // given
        Question question = new Question("Question");
        question = questionService.createQuestion(question);

        // when
        questionService.deleteQuestion(question.getId());

        // then
        assertThat(questionRepository.existsById(question.getId())).isFalse();
    }

    @Test
    void findAllByCategoryId() {
        // given
        Category category = new Category("Category");
        category = categoryService.createCategory(category);

        Question question1 = new Question("Question1");
        question1.setCategory(category);

        Question question2 = new Question("Question2");
        question2.setCategory(category);

        Question question3 = new Question("Question3");
        question3.setCategory(category);

        questionRepository.saveAll(List.of(question1, question2, question3));

        // when
        List<Question> questions = questionService.findAllByCategoryId(category.getId());

        // then
        assertThat(questions).hasSize(3)
                .extracting(Question::getName)
                .containsExactly("Question1", "Question2", "Question3");
    }

    @Test
    void shouldFindHot() {
        // given
        questionRepository.deleteAll();

        Question question1 = new Question("Question1");
        Question question2 = new Question("Question2");
        Question question3 = new Question("Question3");

        questionRepository.saveAll(List.of(question1, question2, question3));

        Answer answer = new Answer("Answer");
        question2.addAnswer(answer);
        answerRepository.save(answer);

        // when
        Page<Question> result = questionService.findHot(Pageable.unpaged());

        // then
        assertThat(result)
                .hasSize(3)
                .extracting(Question::getName)
                .containsExactly("Question2", "Question1", "Question3");
    }

    @Test
    void shouldFindUnanswered() {
        // given
        questionRepository.deleteAll();

        Question question1 = new Question("Question1");
        Question question2 = new Question("Question2");
        Question question3 = new Question("Question3");

        questionRepository.saveAll(List.of(question1, question2, question3));

        Answer answer = new Answer("Answer");
        question2.addAnswer(answer);
        answerRepository.save(answer);

        // when
        Page<Question> result = questionService.findUnanswered(Pageable.unpaged());

        // then
        assertThat(result)
        .hasSize(2)
                .extracting(Question::getName)
                .containsExactly("Question1", "Question3");
    }

    @Test
    void findByQuery() {
        // given
        String query = "abc";

        Question question1 = new Question("Question1");
        Question question2 = new Question("Question2-" + query);
        Question question3 = new Question("Question3");

        questionRepository.saveAll(List.of(question1, question2, question3));

        // when
        Page<Question> result = questionService.findByQuery(query, Pageable.unpaged());

        // then
        assertThat(result)
                .hasSize(1)
                .extracting(Question::getId)
                .containsExactlyInAnyOrder(question2.getId());
    }
}