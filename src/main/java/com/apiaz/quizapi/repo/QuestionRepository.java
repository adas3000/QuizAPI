package com.apiaz.quizapi.repo;

import com.apiaz.quizapi.model.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question,Long> {

}
