package com.quiz.api.service;

import com.quiz.api.enums.Role;
import com.quiz.api.model.User;
import com.quiz.api.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AuthService {

    @Autowired
    private HttpServletRequest request;

    private String sessionHeader = "Session";

    @Autowired
    private UserRepository userRepository;

    public boolean userIsAdmin() {

        String session = this.getSession();

        if (session == null || userRepository.findBySession(session) == null || userRepository.findBySession(session).getRole()!= Role.Admin) return false;

        return true;
    }

    public User getLoggedUser(){
        String session = this.getSession();

        if (session == null || userRepository.findBySession(session) == null) return null;

        return userRepository.findBySession(session);
    }

    private String getSession() {
        return request.getHeader(sessionHeader);
    }

}
