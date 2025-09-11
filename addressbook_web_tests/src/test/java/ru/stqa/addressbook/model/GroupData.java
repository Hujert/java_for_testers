package ru.stqa.addressbook.model;

public record GroupData(String name, String heder, String footer){

    public GroupData() {
        this("","","");
    }

    public GroupData withName(String name) {
        return new GroupData(name, this.heder, this.footer);
    }

    public GroupData withHeder(String heder) {
        return new GroupData(this.name, heder, this.footer);
    }

    public GroupData withFooter(String footer) {
        return new GroupData(this.name, this.heder, footer);
    }
}
