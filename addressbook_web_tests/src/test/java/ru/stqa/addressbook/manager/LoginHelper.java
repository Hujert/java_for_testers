package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginHelper extends HelperBase{

    public LoginHelper(ApplicationManager manager) {
        super(manager);
    }

    void login(String user, String password) {
        WebElement userField = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='user']"))
        );
            type(By.xpath("//input[@name='user']"), user);
            type(By.xpath("//input[@name='pass']"), password);
            click(By.xpath("//input[@value='Login']"));
    }
}
