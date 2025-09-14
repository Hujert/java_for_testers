package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstName : List.of("", "contact firstName")) {
            for (var lastName : List.of("", "contact lastName")) {
                for (var address : List.of("", "contact address")) {
                    for (var mobile : List.of("", "contact mobile")) {
                        for (var email : List.of("", "contact email")) {
                            result.add(new ContactData(firstName, "", lastName, "", "",
                                    "", address, "", mobile, "", "", email, "", "", ""));
                        }
                    }
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData(randomString(i + 10), randomString(i + 10), randomString(i + 10),
                    randomString(i + 10), randomString(i + 10), randomString(i + 10), randomString(i + 10),
                    randomString(i + 10), randomString(i + 10), randomString(i + 10), randomString(i + 10),
                    randomString(i + 10), randomString(i + 10), randomString(i + 10), randomString(i + 10)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContact(ContactData contact) {
        int contactCount = app.contact().getCount();
        app.contact().createContact(contact);
        int newContactCount = app.contact().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }
}
