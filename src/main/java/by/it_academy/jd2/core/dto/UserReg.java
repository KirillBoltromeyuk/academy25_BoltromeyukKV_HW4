package by.it_academy.jd2.core.dto;

import java.time.LocalDateTime;

public class UserReg {
    private String login;
    private String password;
    private String name;
    private String year;
    private String month;
    private String day;

    public UserReg(String login, String password, String name, String year, String month, String day) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public static class Builder {
        private String login;
        private String password;
        private String name;
        private String year;
        private String month;
        private String day;
        public UserReg.Builder setLogin(String login) {
            this.login = login;
            return this;
        }
        public UserReg.Builder setPassword(String password) {
            this.password = password;
            return this;
        }
        public UserReg.Builder setName(String name) {
            this.name = name;
            return this;
        }
        public UserReg.Builder setYear(String year) {
            this.year = year;
            return this;
        }
        public UserReg.Builder setMonth(String month) {
            this.month = month;
            return this;
        }
        public UserReg.Builder setDay(String day) {
            this.day = day;
            return this;
        }
        public UserReg build() {
            return new UserReg(login, password, name, year, month, day);
        }
    }
}
