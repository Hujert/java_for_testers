package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

    @Test
    void canModifyContact() {
        if (!app.contact().isContactPresent()) {
            app.contact().createContact(new ContactData("firstName", "middleName", "lastName", "nickName", "title", "company", "address", "home", "mobile", "work", "fax", "email", "email2", "email3", "homepage"));
        }
        app.contact().modifyContact(new ContactData().withFirstName("modify name"));
    }
}
