package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;

import static ru.stqa.addressbook.manager.ApplicationManager.driver;

public class HelperBase {
    protected final ApplicationManager manager;
    protected WebDriverWait wait;

    public HelperBase(ApplicationManager manager) {
        this.manager = manager;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    protected void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    protected void attach(By locator, String file) {
        driver.findElement(locator).sendKeys(Paths.get(file).toAbsolutePath().toString());
    }

    protected void click(By locator) {
        driver.findElement(locator).click();
    }
}
