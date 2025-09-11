package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase{

    public LoginHelper(ApplicationManager manager) {
        super(manager);
    }

    void login(String user, String password) {
        type(By.xpath("//input[@name='user']"), user);
        type(By.xpath("//input[@name='pass']"), password);
        click(By.xpath("//input[@value='Login']"));
    }
}
