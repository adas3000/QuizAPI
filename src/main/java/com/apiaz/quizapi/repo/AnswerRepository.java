package com.apiaz.quizapi.repo;

import com.apiaz.quizapi.model.Answer;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer,Long> {
}
