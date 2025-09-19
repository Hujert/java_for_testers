package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {
    protected static WebDriver driver;
    private LoginHelper session;
    private GroupHelper groups;
    private ContactHelper contact;

    private Properties properties;

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public LoginHelper session() {
        if (session == null) {
            session = new LoginHelper(this);
        }
        return session;
    }

    public ContactHelper contacts() {
        if (contact == null) {
            contact = new ContactHelper(this);
        }
        return contact;
    }

    public GroupHelper groups() {
        if (groups == null) {
            groups = new GroupHelper(this);
        }
        return groups;
    }

    public void init(Properties properties) {
        if (driver == null) {
            this.properties = properties;
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
            session().login(properties.getProperty("web.username"), properties.getProperty("web.password"));
        }
    }

    public ContactHelper contact() {
        if (contact == null) {
            contact = new ContactHelper(this);
        }
        return contact;
    }

}
