package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;

public class GroupModoficationTests extends TestBase{

    @Test
    void canModifyGroup() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("name", "name Heder", "name Footer"));
        }
        app.groups().modifyGroup(new GroupData().withName("modify name"));
    }
}
