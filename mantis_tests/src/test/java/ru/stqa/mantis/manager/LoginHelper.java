package ru.stqa.mantis.manager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {

    public LoginHelper(ApplicationManager manager) {
        super(manager);
    }

    public void registration(String user) {
        click(By.xpath("//a[@href='signup_page.php']"));
        type(By.xpath("//input[@name='username']"), user);
        type(By.xpath("//input[@name='email']"), String.format("%s@localhost", user));
        click(By.xpath("//input[@type='submit']"));
    }

    public void editAccount(String user, String password) {
        type(By.xpath("//input[@name='realname']"), user);
        type(By.xpath("//input[@name='password']"), password);
        type(By.xpath("//input[@name='password_confirm']"), password);
        click(By.xpath("//button[@type='submit']"));
    }
}
