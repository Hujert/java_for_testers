package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.stqa.addressbook.manager.ApplicationManager.driver;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openContactsPage() {
        if (!manager.isElementPresent(
                By.xpath("//h1[contains(text(),'Edit / add address book entry')]"))) {
            click(By.linkText("add new"));
        }
    }

    public void openHomePage() {
        if (!manager.isElementPresent(
                By.xpath("//input[@value='Send e-Mail']"))) {
            click(By.linkText("home"));
        }
        driver.navigate().refresh();
    }

    public void removeContact(ContactData contact) {
        openHomePage();
        selectContact(contact);
        removeSelectedContact();
        returnToHomePage();
    }

    public void removeAllContact() {
        openHomePage();
        selectAllContact();
        removeSelectedContact();
        returnToHomePage();
    }

    public void createContact(ContactData contact) {
        openContactsPage();
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void createContact(ContactData contact, GroupData group) {
        openContactsPage();
        initContactCreation();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToHomePage();
    }

    public void removeContactFromGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectGroupInContact(group);
        selectContact(contact);
        removeGropeSelectedContact();
        returnToPage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    private void selectGroupInContact(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    private void selectToGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openHomePage();
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }

    private void removeSelectedContact() {
        click(By.xpath("//input[@name='delete']"));
    }

    private void removeGropeSelectedContact() {
        click(By.name("remove"));
    }

    public boolean isContactPresent() {
        openHomePage();
        return manager.isElementPresent(By.xpath("//input[@name='selected[]']"));
    }

    private void submitContactCreation() {
        click(By.xpath("//input[@name='submit']"));
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void returnToHomePage() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'home page')]")));
        click(By.xpath("//a[contains(text(),'home page')]"));
    }

    private void returnToPage() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'group page')]")));
        click(By.xpath("//a[contains(text(),'group page')]"));
    }

    private void submitContactModification() {
        click(By.xpath("//input[@name='update']"));

    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("middlename"), contact.middleName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("nickname"), contact.nickName());
        type(By.name("title"), contact.title());
        type(By.name("company"), contact.company());
        type(By.name("address"), contact.address());
        type(By.name("home"), contact.home());
        type(By.name("mobile"), contact.mobile());
        type(By.name("work"), contact.work());
        type(By.name("fax"), contact.fax());
        type(By.name("email"), contact.email());
        type(By.name("email2"), contact.email2());
        type(By.name("email3"), contact.email3());
        type(By.name("homepage"), contact.homepage());
//        if (contact.photo() != null && !contact.photo().isEmpty()) {
//            attach(By.name("photo"), contact.photo());
//        }
    }

    private void initContactModification(ContactData contact) {
        click(By.cssSelector(String.format("tr:has(input[value='%s']) img[title='Edit']", contact.id())));
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    private void selectAllContact() {
        click(By.xpath("//input[@onclick='MassSelection()']"));
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getList() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.name("entry"));
        for (var tr : trs) {
            var lastName = tr.findElement(By.cssSelector("td:nth-child(2)")).getText();
            var firstName = tr.findElement(By.cssSelector("td:nth-child(3)")).getText();
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("id");
            contacts.add(new ContactData().withId(id).withLastName(lastName).withFirstName(firstName));
        }
        return contacts;
    }

    public String getPhone(ContactData contact) {
        return manager.driver.findElement(By.xpath(String
                .format("//input[@id='%s']//ancestor::tr/td[6]", contact.id()))).getText();
    }

    public Map<String, String> getPhones() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    public Map<String, String> getAddresses() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement (By.tagName("input")).getAttribute("id");
            var addresses = row.findElements(By.tagName("td")).get(3).getText();
            result.put(id, addresses);
        }
        return result;
    }

    public Map<String, String> getEmails() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement (By.tagName("input")).getAttribute("id");
            var emails = row.findElements(By.tagName("td")).get(4).getText();
            result.put(id, emails);
        }
        return result;
    }

    public Map<String, String> getContactData() {
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var addresses = row.findElements(By.tagName("td")).get(3).getText();
            var emails = row.findElements(By.tagName("td")).get(4).getText();
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    public void addContactToGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectToGroup(group);
        selectContact(contact);
        addContactToGrope();
        returnToPage();
    }

    private void addContactToGrope() {
        click(By.xpath("//input[@name='add']"));
    }
}
