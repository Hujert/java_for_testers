package ru.stqa.addressbook.manager;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernatePersistenceConfiguration;
import ru.stqa.addressbook.manager.hbm.ContactRecord;
import ru.stqa.addressbook.manager.hbm.GroupRecord;
import ru.stqa.addressbook.model.ContactData;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase {
    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new HibernatePersistenceConfiguration("Addressbook")
                .managedClass(GroupRecord.class)
                .managedClass(ContactRecord.class)
                .jdbcUrl("jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                .jdbcCredentials("root", "")
                .createEntityManagerFactory();
    }

    static List<GroupData> converGrouptList(List<GroupRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    static List<ContactData> convertContactList(List<ContactRecord> records) {
        List<ContactData> result = new ArrayList<>();
        for (var record : records) {
            result.add(convert(record));
        }
        return result;
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static ContactData convert(ContactRecord record) {
        return new ContactData().withId("" + record.id).withFirstName(record.firstname)
                .withLastName(record.lastname).withAddress(record.address).withHome(record.home)
                .withMobile(record.mobile).withWork(record.work).withSecondary(record.phone2)
                .withEmail(record.email).withEmail2(record.email2).withEmail3(record.email3);
    }

    private static GroupRecord convert(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    private static ContactRecord convert(ContactData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), data.firstName(), data.lastName(), data.address());
    }

    public List<GroupData> getGroupList() {
        return converGrouptList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }


    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public List<ContactData> getContactList() {
        return convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }


    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData data) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(data));
            session.getTransaction().commit();
        });
    }

    public void createContact(ContactData data) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(data));
            session.getTransaction().commit();
        });
    }

    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.get(GroupRecord.class, group.id()).contacts);});
    }
}
