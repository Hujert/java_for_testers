package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class ContactRemovalTests extends TestBase{

    @Test
    public void canRemoveContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "firstName", "middleName", "lastName", "nickName", "title", "company", "address", "home", "mobile", "work", "fax", "email", "email2", "email3", "homepage", ""));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        app.contacts().removeContact(oldContacts.get(index));
        var newContact = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Assertions.assertEquals(newContact, expectedList);
    }

    @Test
    public void canRemoveAllContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "firstName", "middleName", "lastName", "nickName", "title", "company", "address", "home", "mobile", "work", "fax", "email", "email2", "email3", "homepage", ""));
        }
        app.contacts().removeAllContact();
        var newList = app.hbm().getContactList();
        var expectedList = Collections.emptyList();
        Assertions.assertEquals(expectedList, newList);
    }

    @Test
    public void canCreateContactInGroup() {
        var contact = new ContactData()
                .withMiddleName(CommonFunctions.randomString(10))
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withAddress(CommonFunctions.randomString(10));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name",
                    "group header", "group footer"));
        }
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        oldRelated.sort(compareById);
        newRelated.sort(compareById);
        var newContact = newRelated.get(newRelated.size() - 1);
        app.contacts().removeContactFromGroup(newContact, group);
        ArrayList<ContactData> expectedRelated = new ArrayList<>(oldRelated);
        expectedRelated.remove(newContact);
        expectedRelated.sort(compareById);
        Assertions.assertEquals(expectedRelated, oldRelated);
    }
}
