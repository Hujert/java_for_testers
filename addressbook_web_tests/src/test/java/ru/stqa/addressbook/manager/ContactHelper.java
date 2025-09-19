package ru.stqa.addressbook.manager;

import org.openqa.selenium.By;
import ru.stqa.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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
        click(By.xpath("//a[contains(text(),'home page')]"));
    }

    private void submitContactModification() {
        click(By.xpath("//input[@name='update']"));

    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("middlename"), contact.middleName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("nickname"), contact.lastName());
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
        if (contact.photo() != null) {
            attach(By.name("photo"), contact.photo());
        } else {
        }

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
}
