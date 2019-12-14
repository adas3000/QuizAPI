package com.apiaz.quizapi.repo;

import com.apiaz.quizapi.model.Choice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoiceRepository extends CrudRepository<Choice,Long> {
}
