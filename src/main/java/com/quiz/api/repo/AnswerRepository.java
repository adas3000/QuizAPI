package com.quiz.api.repo;

import com.quiz.api.model.Answer;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer,Long> {
}
