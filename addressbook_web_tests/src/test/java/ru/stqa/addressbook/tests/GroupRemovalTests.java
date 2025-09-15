package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import ru.stqa.addressbook.model.GroupData;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupRemovalTests extends TestBase{

    @Test
    public void canRemoveGrope() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "name", "name Heder", "name Footer"));
        }
        var oldGroups = app.groups().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        var newGroups = app.groups().getList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @Test
    public void canRemoveAllGropes() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "name", "name Heder", "name Footer"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.groups().getCount());
    }


}
