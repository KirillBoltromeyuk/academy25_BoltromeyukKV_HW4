package by.it_academy.jd2.dto;

import java.time.LocalDateTime;

public class User {
    private String login;
    private String password;
    private String name;
    private LocalDateTime dateOfBirth;
    private LocalDateTime dateOfCreate;
    private UserRole role;

    private User(String login, String password, String name, LocalDateTime dateOfBirth, LocalDateTime dateOfCreate, UserRole role) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.dateOfCreate = dateOfCreate;
        this.role = role;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(LocalDateTime dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public static class UserBuilder {
        private String login;
        private String password;
        private String name;
        private LocalDateTime dateOfBirth;
        private LocalDateTime dateOfCreate = LocalDateTime.now();
        private UserRole role= UserRole.USER;
        public UserBuilder setLogin(String login) {
            this.login = login;
            return this;
        }
        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }
        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }
        public UserBuilder setDateOfBirth(LocalDateTime dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }
        public UserBuilder setDateOfCreate(LocalDateTime dateOfCreate) {
            this.dateOfCreate = dateOfCreate;
            return this;
        }
        public UserBuilder setRole(UserRole role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(login, password, name, dateOfBirth, dateOfCreate, role);
        }
    }
}
