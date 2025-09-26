package ru.stqa.addressbook.tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ru.stqa.addressbook.manager.ApplicationManager;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

    protected static ApplicationManager app;

    @BeforeEach
    @Description("Выполняется перед каждым тестом")
    public void setUp() throws IOException {
        Properties properties = null;
        if (app == null) {
            properties = new Properties();
            properties.load(new FileReader(System.getProperty("target", "local.properties")));
            app = new ApplicationManager();
        }
        app.init(properties);
    }

//    @AfterEach
//    @Description("Выполняется после каждого теста")
//    void chackDatabaseConsistency() {
//        app.jdbc().checkConsistency();
//
//    }

}
