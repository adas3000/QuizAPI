package com.apiaz.quizapi.repo;

import com.apiaz.quizapi.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface QuestionRepository extends CrudRepository<Question,Long> {
    Optional<Question> findByValue(@Param("value")String value);
}
