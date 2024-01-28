package pl.slawek.ideas.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.slawek.ideas.domain.model.Question;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {

    List<Question> findAllByCategoryId(UUID id);

    @Query("from Question q order by size(q.answers) desc")
    Page<Question> findHot(Pageable pageable);

    @Query("from Question q where size(q.answers) = 0")
    Page<Question> findUnanswered(Pageable pageable);

    @Query(nativeQuery = true,
            value = "select * from questions q where upper(q.name) like upper('%' || :query || '%')",
            countQuery = "select count(*) from questions q where upper(q.name) like upper('%' || :query || '%')")
    Page<Question> findByQuery(String query, Pageable pageable);
}
