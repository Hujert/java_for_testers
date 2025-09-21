package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static ru.stqa.addressbook.generator.Generator.randomFile;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
        for (var firstName : List.of("", "contact firstName")) {
            for (var lastName : List.of("", "contact lastName")) {
                for (var address : List.of("", "address")) {
                        result.add(new ContactData()
                                .withFirstName(firstName)
                                .withLastName(lastName)
                                .withAddress(address));
                }
            }
        }
        var mapper = new XmlMapper();
        var value = mapper.readValue(new File("contacts.xml"), new TypeReference<List<ContactData>>() {
        });
        result.addAll(value);
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContact(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        ArrayList<ContactData> expectedContacts = new ArrayList<>(oldContacts);
        expectedContacts.add(newContacts.get(newContacts.size() - 1));
        expectedContacts.sort(compareById);
        Assertions.assertEquals(expectedContacts, newContacts);
    }

    @Test
    public void canCreateContactInGroup() {
        var contact = new ContactData()
                .withMiddleName(CommonFunctions.randomString(10))
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withTitle(CommonFunctions.randomString(10))
                .withCompany(CommonFunctions.randomString(10))
                .withAddress(CommonFunctions.randomString(10))
                .withHome(CommonFunctions.randomString(10))
                .withWork(CommonFunctions.randomString(10));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name",
                    "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        var allContacts = app.hbm().getContactList();
        var newContact = allContacts.stream()
                .filter(c -> c.firstName().equals(contact.firstName()) &&
                        c.lastName().equals(contact.lastName()))
                .findFirst()
                .orElseThrow();
        ArrayList<ContactData> expectedRelated = new ArrayList<>(oldRelated);
        expectedRelated.add(newContact);
        Assertions.assertEquals(expectedRelated, newRelated);
    }
}
