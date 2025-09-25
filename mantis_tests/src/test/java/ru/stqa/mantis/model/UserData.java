package ru.stqa.mantis.model;

import io.swagger.client.model.AccessLevel;

public record UserData(String username, String email, String password) {

    public UserData() {
        this("", "", "");
    }
//
    public UserData withUsername (String username) {
        return new UserData(username, this.email, this.password);
    }

    public UserData withEmail (String email) {
        return new UserData(this.username, email, this.password);
    }

    public UserData withPassword (String realName) {
        return new UserData(this.username, this.email, password);
    }
//
//    public UserData withCategory (AccessLevel accessLevel) {
//        return new UserData(this.username, this.email, this.realName, accessLevel);
//    }
}
