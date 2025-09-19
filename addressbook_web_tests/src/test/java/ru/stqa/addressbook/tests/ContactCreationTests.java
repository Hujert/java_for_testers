package ru.stqa.addressbook.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.model.ContactData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
        for (var firstName : List.of("", "contact firstName")) {
            for (var lastName : List.of("", "contact lastName")) {
                        for (var photo : List.of("src/test/resources/images/avatar.png")) {
                            result.add(new ContactData()
                                    .withFirstName(firstName)
                                    .withLastName(lastName)
                                    .withPhoto(photo));
                }
            }
        }
        var mapper = new XmlMapper();
        var value = mapper.readValue(new File("contacts.xml"), new TypeReference<List<ContactData>>() {});
        result.addAll(value);
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContact(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contact().createContact(contact);
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        ArrayList<ContactData> expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact
                .withId(newContacts.get(newContacts.size() - 1).id())
                .withMiddleName("")
                .withNickName("")
                .withTitle("")
                .withCompany("")
                .withAddress("")
                .withHome("")
                .withMobile("")
                .withWork("")
                .withFax("")
                .withEmail("")
                .withEmail2("")
                .withEmail3("")
                .withHomePage("")
                .withPhoto("")
        );
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }
}
