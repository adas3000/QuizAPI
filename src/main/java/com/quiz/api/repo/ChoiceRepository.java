package com.quiz.api.repo;

import com.quiz.api.model.Choice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoiceRepository extends CrudRepository<Choice,Long> {
}
