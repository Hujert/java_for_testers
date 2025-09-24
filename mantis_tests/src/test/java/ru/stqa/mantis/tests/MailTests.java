package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class MailTests extends TestBase {

    @Test
    @Disabled
        //Debug test
    void canDrainInbox() {
        app.mail().drain("user1@localhost", "password");
    }

    @Test
    @Disabled
        //Debug test
    void canReceiveEmail() {
        var messages = app.mail().receive("user1@localhost", "password", Duration.ofSeconds(60));
        Assertions.assertEquals(1, messages.size());
        System.out.println(messages);
    }

    @Test
    @Disabled
        //Debug test
    void canExtractUrl() {
        var url = app.mail().getUrlFromMail("user1@localhost", "password");
        System.out.println(url);
    }
}
