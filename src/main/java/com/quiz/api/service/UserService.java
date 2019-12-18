package com.quiz.api.service;

import com.quiz.api.enums.Role;
import com.quiz.api.model.User;
import com.quiz.api.repo.UserRepository;
import com.quiz.api.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    public ResponseEntity<Object> login(LoginRequest loginRequest) {

        if (loginRequest.login == null || loginRequest.password == null){
            return new ResponseEntity<>("invalid_login_request_params", HttpStatus.BAD_REQUEST);
        }

        User u = userRepository.findByLoginAndPassword(loginRequest.login, DigestUtils.md5DigestAsHex(loginRequest.password.getBytes()));

        if(u==null){
            return new ResponseEntity<>("no_such_user",HttpStatus.NOT_FOUND);
        }

        u.setSession(UUID.randomUUID().toString());

        Object resultUserDetails = new Object() {
            public final String login = u.getLogin();
            public final String session = u.getSession();
            public final Role role = u.getRole();
        };

        return new ResponseEntity<>(resultUserDetails,HttpStatus.OK);
    }

    public ResponseEntity<Object> logout(){

        User u = authService.getLoggedUser();

        if(u==null){
            return new ResponseEntity<>("no_logged_in",HttpStatus.BAD_REQUEST);
        }
        u.setSession(null);
        return new ResponseEntity<>("logged_out",HttpStatus.OK);
    }

}
