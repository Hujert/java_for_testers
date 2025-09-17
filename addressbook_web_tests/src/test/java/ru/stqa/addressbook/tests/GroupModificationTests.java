package ru.stqa.addressbook.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GroupModificationTests extends TestBase{

    @Test
    void canModifyGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "name", "name Heder", "name Footer"));
        }
        var oldGroups = app.groups().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var updateData = new GroupData().withName("modify name");
        app.groups().modifyGroup(oldGroups.get(index), updateData);
        var newGroups = app.groups().getList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, updateData.withId(oldGroups.get(index).id()));
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups, expectedList);
    }
}
