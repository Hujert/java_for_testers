package ru.stqa.mantis.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.mantis.tests.TestBase;

import java.util.Properties;

public class ApplicationManager {

    private WebDriver driver;
    private String browser;
    private Properties properties;
    private String string;
    private SessionHelper sessionHelper;
    private HttpSessionHelper httpSessionHelper;
    private JamesCliHelper jamesCliHelper;
    private MailHelper mailHelper;
    private LoginHelper loginHelper;

    public void init(String browser, Properties properties) {
        this.browser = browser;
        this.properties = properties;
    }

        public WebDriver driver () {
            if (driver == null) {
                if ("firefox".equals(properties.getProperty("browser.default"))) {
                    driver = new FirefoxDriver();
                } else if ("chrome".equals(properties.getProperty("browser.default"))) {
                    driver = new ChromeDriver();
                } else {
                    throw new IllegalArgumentException(String.format("Unknown browser %s", properties.getProperty("browser.default")));
                }
                Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
                driver.get(properties.getProperty("web.baseUrl"));
                driver.manage().window().setSize(new Dimension(1389, 693));
            }
            return driver;
        }


    public SessionHelper session() {
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }

    public HttpSessionHelper http() {
        if (httpSessionHelper == null) {
            httpSessionHelper = new HttpSessionHelper(this);
        }
        return httpSessionHelper;
    }

    public JamesCliHelper jamsCli() {
        if (jamesCliHelper == null) {
            jamesCliHelper = new JamesCliHelper(this);
        }
        return jamesCliHelper;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public LoginHelper login() {
        if (loginHelper == null) {
            loginHelper = new LoginHelper(this);
        }
        return loginHelper;
    }

    public String property(String name) {
        return properties.getProperty(name);
    }
}

