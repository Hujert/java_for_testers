package ru.stqa.mantis.tests;

import io.swagger.client.ApiException;
import io.swagger.client.api.UserApi;
import io.swagger.client.model.User;
import io.swagger.client.model.UserAddResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;

import java.io.IOException;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser() {
        var username = CommonFunctions.randomString(8);
        var email = String.format("%s@localhost", username);
        var password = "password";
        try {
            app.jamsCli().addUser(email, password);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        app.login().registration(username);
        app.mail().getInbox(email, password);
        var url = app.mail().getUrlFromMail(email, password);
        app.driver().get(url);
        app.login().editAccount(username, password);
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @Test
    void canRegisterNewUserApi() {
        var username = CommonFunctions.randomString(8);
        var email = String.format("%s@localhost", username);
        var password = "password";
        try {
            app.jamesApi().addUser(email, password);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        app.rest().createUser(username, email, password);
        app.mail().getInbox(email, password);
        var url = app.mail().getUrlFromMail(email, password);
        app.driver().get(url);
        app.login().editAccount(username, password);
        app.http().login(username, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
