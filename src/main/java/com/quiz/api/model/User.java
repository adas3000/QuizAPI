package com.quiz.api.model;

import com.quiz.api.enums.Role;

import javax.persistence.*;

@Entity
@Table(name = "_user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String login;

    private String password;

    @Enumerated
    private Role role;

    private String session;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
