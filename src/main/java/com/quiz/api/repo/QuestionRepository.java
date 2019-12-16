package com.quiz.api.repo;

import com.quiz.api.enums.Category;
import com.quiz.api.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

public interface QuestionRepository extends CrudRepository<Question,Long> {
    Optional<Question> findByValue(@Param("value")String value);
    List<Question> findAllByCategory(@Param("category")Category category);
}
