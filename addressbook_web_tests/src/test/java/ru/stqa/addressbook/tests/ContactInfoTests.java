package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                        .filter(s -> s != null & !"".equals(s))
                        .collect(Collectors.joining("\n"))));
        var phones = app.contacts().getPhones();
        assertEquals(expected, phones);
    }

    @Test
    void testAddresses() {
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.address())
                        .filter(s -> s != null & !"".equals(s))
                        .collect(Collectors.joining("\n"))));
        var addresses = app.contacts().getAddresses();
        assertEquals(expected, addresses);
    }

    @Test
    void testEmails() {
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.email(), contact.email2(), contact.email3())
                        .filter(s -> s != null & !"".equals(s))
                        .collect(Collectors.joining("\n"))));
        var emails = app.contacts().getEmails();
        assertEquals(expected, emails);
    }

    @Test
    void testFirstContactPhonesAddressesEmails() {
        var contact = app.hbm().getContactList().get(0);
        var expectedEmail = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && !s.trim().isEmpty())
                .map(String::trim)
                .collect(Collectors.joining("\n"));
        var emails = app.contacts().getEmails().get(String.valueOf(contact.id()));

        var expectedAddress = Stream.of(contact.address())
                .filter(s -> s != null && !s.trim().isEmpty())
                .map(String::trim)
                .collect(Collectors.joining("\n"));
        var addresses = app.contacts().getAddresses().get(String.valueOf(contact.id()));

        var expectedPhones = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                .filter(s -> s != null && !s.trim().isEmpty())
                .map(String::trim)
                .collect(Collectors.joining("\n"));
        var phones = app.contacts().getPhones().get(String.valueOf(contact.id()));

        Assertions.assertAll("Проверяем соответствие записей phones, addresses, emails",
                () -> assertEquals(expectedPhones, phones, "Phones не соответствует ожидаемому"),
                () -> assertEquals(expectedAddress, addresses, "Addresses не соответствует ожидаемому"),
                () -> assertEquals(expectedEmail, emails, "Emails не соответствует ожидаемому"));
    }


}
