package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.ContactData;

public class ContactRemovalTests extends TestBase{

    @Test
    public void canRemoveContact() {
        if (!app.contact().isContactPresent()) {
            app.contact().createContact(new ContactData("firstName", "middleName", "lastName", "nickName", "title", "company", "address", "home", "mobile", "work", "fax", "email", "email2", "email3", "homepage"));
        }
        app.contact().removeContact();
    }

    @Test
    public void canRemoveAllContact() {
        if (!app.contact().isContactPresent()) {
            app.contact().createContact(new ContactData("firstName", "middleName", "lastName", "nickName", "title", "company", "address", "home", "mobile", "work", "fax", "email", "email2", "email3", "homepage"));
        }
        app.contact().removeAllContact();
    }
}
