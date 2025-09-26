package ru.stqa.addressbook.tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactInfoTests extends TestBase {

    @Test
    @Description("Тест проверяет соответствие номеров телефона на странице списка пользователей и в DB")
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
    @Description("Тест проверяет соответствие адресов на странице списка пользователей и в DB")
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
    @Description("Тест проверяет соответствие почты на странице списка пользователей и в DB")
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
    @Description("Комплексный тест проверяет соответствие телефонов, адресов и почты на странице списка у конкретного пользователя и в DB")
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

    @Test
    @Description("Комплексный тест проверяет соответствие телефонов, адресов и почты на странице списка пользователей и в DB")
    void testAllContactPhonesAddressesEmails() {
        var contacts = app.hbm().getContactList();
        var expectedEmail = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.email(), contact.email2(), contact.email3())
                        .filter(s -> s != null & !"".equals(s))
                        .collect(Collectors.joining("\n"))));
        var emails = app.contacts().getEmails();

        var expectedAddress = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.address())
                        .filter(s -> s != null & !"".equals(s))
                        .collect(Collectors.joining("\n"))));
        var addresses = app.contacts().getAddresses();

        var expectedPhones = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                        .filter(s -> s != null & !"".equals(s))
                        .collect(Collectors.joining("\n"))));
        var phones = app.contacts().getPhones();

        Assertions.assertAll("Проверяем соответствие записей phones, addresses, emails",
                () -> assertEquals(expectedPhones, phones, "Phones не соответствует ожидаемому"),
                () -> assertEquals(expectedAddress, addresses, "Addresses не соответствует ожидаемому"),
                () -> assertEquals(expectedEmail, emails, "Emails не соответствует ожидаемому"));
    }


}
