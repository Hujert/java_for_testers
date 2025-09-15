package ru.stqa.addressbook.model;

public record GroupData(String id, String name, String heder, String footer){

    public GroupData() {
        this("", "","","");
    }

    public GroupData withId(String id) {
        return new GroupData(id, this.name, this.heder, this.footer);
    }

    public GroupData withName(String name) {
        return new GroupData(this.id, name, this.heder, this.footer);
    }

    public GroupData whithHeader(String heder) {
        return new GroupData(this.id, this.name, heder, this.footer);
    }

    public GroupData whithFooter(String footer) {
        return new GroupData(this.id, this.name, this.heder, footer);
    }
}
