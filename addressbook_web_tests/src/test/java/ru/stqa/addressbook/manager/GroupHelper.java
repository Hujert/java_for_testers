package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;
import ru.stqa.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openGroupsPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.xpath("//a[contains(text(),'groups')]"));
        }
    }

    public void removeGroup() {
        openGroupsPage();
        selectGroup();
        removeSelectedGroup();
        returnToGroupsPage();
    }

    public void createGroup(GroupData group) {
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        returnToGroupsPage();
    }

    public void modifyGroup(GroupData modifiedGroup) {
        openGroupsPage();
        selectGroup();
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPage();
    }

    private void removeSelectedGroup() {
        click(By.xpath("//input[@name='delete']"));
    }

    public boolean isGroupPresent() {
        openGroupsPage();
        return manager.isElementPresent(By.xpath("//input[@name='selected[]']"));
    }

    private void submitGroupCreation() {
        click(By.xpath("//input[@name='submit']"));
    }

    private void initGroupCreation() {
        click(By.xpath("//input[@name='new']"));
    }

    private void returnToGroupsPage() {
        click(By.xpath("//a[contains(text(),'group page')]"));
    }

    private void submitGroupModification() {
        click(By.xpath("//input[@name='update']"));

    }

    private void fillGroupForm(GroupData group) {
        type(By.xpath("//input[@name='group_name']"), group.name());
        type(By.xpath("//textarea[@name='group_header']"), group.heder());
        type(By.xpath("//textarea[@name='group_footer']"), group.footer());

    }

    private void initGroupModification() {
        click(By.xpath("//input[@name='edit']"));
    }

    private void selectGroup() {
        click(By.xpath("//input[@name='selected[]']"));
    }

}
