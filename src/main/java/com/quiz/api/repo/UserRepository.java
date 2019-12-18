package com.quiz.api.repo;

import com.quiz.api.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findBySession(@Param("session")String session);
    User findByLoginAndPassword(@Param("login")String login,@Param("password")String password);
}
