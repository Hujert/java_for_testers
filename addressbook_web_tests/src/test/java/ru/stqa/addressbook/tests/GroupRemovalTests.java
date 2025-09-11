package ru.stqa.addressbook.tests;

import ru.stqa.addressbook.model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupRemovalTests extends TestBase{

    @Test
    public void canRemoveGrope() {
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("name", "name Heder", "name Footer"));
        }
        app.groups().removeGroup();
    }
}
