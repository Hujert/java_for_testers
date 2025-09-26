package ru.stqa.addressbook.tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.addressbook.common.CommonFunctions;
import ru.stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;

public class GroupModificationTests extends TestBase{

    @Test
    @Description("Тест проверяет функционал редактирования группы")
    void canModifyGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "name", "name Heder", "name Footer"));
        }
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var updateData = new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(10))
                .withFooter(CommonFunctions.randomString(10));
        app.groups().modifyGroup(oldGroups.get(index), updateData);
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, updateData.withId(oldGroups.get(index).id()));
        Assertions.assertEquals(Set.copyOf(expectedList), Set.copyOf(newGroups));
    }
}
