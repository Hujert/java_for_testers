package ru.stqa.addressbook.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import ru.stqa.addressbook.model.GroupData;
import org.junit.jupiter.api.Test;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GroupRemovalTests extends TestBase{

    @Test
    @Description("Тест удаляет группу")
    public void canRemoveGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "name", "name Heder", "name Footer"));
        }
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Allure.step("Validating result", step -> {
                Assertions.assertEquals(newGroups, expectedList);
        });
    }

    @Test
    @Generated("Тест удаляет все группы")
    public void canRemoveAllGropes() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "name", "name Heder", "name Footer"));
        }
        app.groups().removeAllGroups();
        var newList = app.hbm().getGroupList();
        var expectedList = Collections.emptyList();
        Assertions.assertEquals(expectedList, newList);
    }


}
