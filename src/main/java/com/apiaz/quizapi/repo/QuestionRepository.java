package com.apiaz.quizapi.repo;

import com.apiaz.quizapi.enums.Category;
import com.apiaz.quizapi.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

public interface QuestionRepository extends CrudRepository<Question,Long> {
    Optional<Question> findByValue(@Param("value")String value);
    List<Question> findAllByCategory(@Param("category")Category category);
}
