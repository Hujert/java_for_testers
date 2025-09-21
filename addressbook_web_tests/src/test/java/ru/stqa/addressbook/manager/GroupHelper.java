package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;
import static ru.stqa.addressbook.manager.ApplicationManager.driver;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openGroupsPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.xpath("//a[contains(text(),'groups')]"));
        }
        driver.navigate().refresh();
    }

    public void removeGroup(GroupData group) {
        openGroupsPage();
        selectGroup(group);
        removeSelectedGroups();
        returnToGroupsPage();
    }

    public void removeAllGroups() {
        openGroupsPage();
        selectAllGroups();
        removeSelectedGroups();
        returnToGroupsPage();
    }

    public void createGroup(GroupData group) {
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }

    public void modifyGroup(GroupData group, GroupData modifiedGroup) {
        openGroupsPage();
        selectGroup(group);
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPage();
    }

    private void removeSelectedGroups() {
        click(By.xpath("//input[@name='delete']"));
    }

    private void submitGroupCreation() {
        click(By.xpath("//input[@name='submit']"));
    }

    private void initGroupCreation() {
        click(By.xpath("//input[@name='new']"));
    }

    private void returnToGroupsPage() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'group page')]")));
        click(By.xpath("//a[contains(text(),'group page')]"));
    }

    private void submitGroupModification() {
        click(By.xpath("//input[@name='update']"));

    }

    private void fillGroupForm(GroupData group) {
        type(By.xpath("//input[@name='group_name']"), group.name());
        type(By.xpath("//textarea[@name='group_header']"), group.header());
        type(By.xpath("//textarea[@name='group_footer']"), group.footer());

    }

    private void initGroupModification() {
        click(By.xpath("//input[@name='edit']"));
    }

    private void selectGroup(GroupData group) {
        click(By.cssSelector(String.format("input[value='%s']", group.id())));
    }

    public int getCount() {
        openGroupsPage();
        return driver.findElements(By.xpath("//input[@name='selected[]']")).size();
    }

    private void selectAllGroups() {
        manager.driver.findElements(By.xpath("//input[@name='selected[]']"))
                .forEach(WebElement::click);
    }

    public List<GroupData> getList() {
        openGroupsPage();
        var spans = driver.findElements(By.cssSelector("span.group"));
        return spans.stream()
                .map(span -> {
                    var name = span.getText();
                    var checkbox = span.findElement(By.name("selected[]"));
                    var id = checkbox.getAttribute("value");
                    return new GroupData().withId(id).withName(name);
                })
                .collect(Collectors.toList());

    }
}
