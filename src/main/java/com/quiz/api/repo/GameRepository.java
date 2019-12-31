package com.quiz.api.repo;

import com.quiz.api.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface GameRepository extends CrudRepository<Game,Long> {
    Game findByGameUUID(@Param("GameUUID")String GameUUID);
}
