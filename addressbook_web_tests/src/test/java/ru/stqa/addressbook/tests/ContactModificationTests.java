package ru.stqa.addressbook.tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class ContactModificationTests extends TestBase {

    @Test
    @Description("Тест проверяет возможность редактирования контакта")
    void canModifyContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withMiddleName(CommonFunctions.randomString(10))
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withAddress(CommonFunctions.randomString(10)));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var updateData = new ContactData().withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10));
        app.contacts().modifyContact(oldContacts.get(index), updateData);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, updateData.withId(oldContacts.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newContacts);
    }

    @Test
    @Description("Тест проверяет функционал удаления пользователя из группы")
    public void removeContactFromGroup() {
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

    @Test
    @Description("Тест проверяет функционал добавления контакта в группу")
    public void testAddContactToGroup() {
        if (app.hbm().getContactCount() == app.hbm().getContactsCountInAnyGroup()) {
            app.hbm().createContact(new ContactData()
                    .withMiddleName(CommonFunctions.randomString(10))
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withAddress(CommonFunctions.randomString(10)));
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData()
                    .withName(CommonFunctions.randomString(10))
                    .withHeader(CommonFunctions.randomString(20))
                    .withFooter(CommonFunctions.randomString(30)));
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        var contactsList = app.hbm().getContactList();
        var contacts = contactsList.stream().filter(g -> ! oldRelated.contains(g)).collect(Collectors.toList());
        app.contacts().addContactToGroup(contacts.get(0), group);
        var newRelated = app.hbm().getContactsInGroup(group);
        ArrayList<ContactData> expectedRelated = new ArrayList<>(oldRelated);
        expectedRelated.add(contacts.get(0));
        Assertions.assertEquals(Set.copyOf(expectedRelated), Set.copyOf(newRelated));
    }
}
