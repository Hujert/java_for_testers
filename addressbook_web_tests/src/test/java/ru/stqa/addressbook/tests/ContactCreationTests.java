package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContact() {
        app.contact().createContact(new ContactData("firstName", "middleName", "lastName", "nickName", "title", "company", "address", "home", "mobile", "work", "fax", "email", "email2", "email3", "homepage"));
    }

    @Test
    public void canCreateContactWithEmptyName() {
        app.contact().createContact(new ContactData());
    }

    @Test
    public void canCreateContactWithNameOnly() {
        app.contact().createContact(new ContactData().withFirstName("some first name"));
    }
}
