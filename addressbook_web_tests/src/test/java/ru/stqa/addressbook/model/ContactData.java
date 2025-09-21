package ru.stqa.addressbook.model;

public record ContactData
        (
                String id,
                String firstName,
                String middleName,
                String lastName,
                String nickName,
                String title,
                String company,
                String address,
                String home,
                String mobile,
                String work,
                String fax,
                String email,
                String email2,
                String email3,
                String homepage,
                String photo,
                String secondary
        ) {

    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, this.company, this.address, this.home, this.mobile,
                this.work, this.fax, this.email, this.email2, this.email3, this.homepage, this.photo, this.secondary);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.middleName, this.lastName, this.nickName,
                this.title, this.company, this.address, this.home, this.mobile,
                this.work, this.fax, this.email, this.email2, this.email3, this.homepage, this.photo, this.secondary);
    }

    public ContactData withMiddleName(String middleName) {
        return new ContactData(this.id, this.firstName, middleName, this.lastName, this.nickName,
                this.title, this.company, this.address, this.home, this.mobile,
                this.work, this.fax, this.email, this.email2, this.email3, this.homepage, this.photo, this.secondary);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, this.middleName, lastName, this.nickName,
                this.title, this.company, this.address, this.home, this.mobile,
                this.work, this.fax, this.email, this.email2, this.email3, this.homepage, this.photo, this.secondary);
    }

    public ContactData withNickName(String nickName) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, nickName,
                this.title, this.company, this.address, this.home, this.mobile,
                this.work, this.fax, this.email, this.email2, this.email3, this.homepage, this.photo, this.secondary);
    }

    public ContactData withTitle(String title) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.nickName,
                title, this.company, this.address, this.home, this.mobile,
                this.work, this.fax, this.email, this.email2, this.email3, this.homepage, this.photo, this.secondary);
    }

    public ContactData withCompany(String company) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, company, this.address, this.home, this.mobile,
                this.work, this.fax, this.email, this.email2, this.email3, this.homepage, this.photo, this.secondary);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, this.company, address, this.home, this.mobile,
                this.work, this.fax, this.email, this.email2, this.email3, this.homepage, this.photo, this.secondary);
    }

    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, this.company, this.address, home, this.mobile,
                this.work, this.fax, this.email, this.email2, this.email3, this.homepage, this.photo, this.secondary);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, this.company, this.address, this.home, mobile,
                this.work, this.fax, this.email, this.email2, this.email3, this.homepage, this.photo, this.secondary);
    }

    public ContactData withWork(String work) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, this.company, this.address, this.home, this.mobile,
                work, this.fax, this.email, this.email2, this.email3, this.homepage, this.photo, this.secondary);
    }

    public ContactData withFax(String fax) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, this.company, this.address, this.home, this.mobile,
                this.work, fax, this.email, this.email2, this.email3, this.homepage, this.photo, this.secondary);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, this.company, this.address, this.home, this.mobile,
                this.work, this.fax, email, this.email2, this.email3, this.homepage, this.photo, this.secondary);
    }

    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, this.company, this.address, this.home, this.mobile,
                this.work, this.fax, this.email, email2, this.email3, this.homepage, this.photo, this.secondary);
    }

    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, this.company, this.address, this.home, this.mobile,
                this.work, this.fax, this.email, this.email2, email3, this.homepage, this.photo, this.secondary);
    }

    public ContactData withHomePage(String homePage) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, this.company, this.address, this.home, this.mobile,
                this.work, this.fax, this.email, this.email2, this.email3, homePage, this.photo, this.secondary);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, this.company, this.address, this.home, this.mobile,
                this.work, this.fax, this.email, this.email2, this.email3, this.homepage, photo, this.secondary);
    }

    public ContactData withSecondary(String secondary) {
        return new ContactData(this.id, this.firstName, this.middleName, this.lastName, this.nickName,
                this.title, this.company, this.address, this.home, this.mobile,
                this.work, this.fax, this.email, this.email2, this.email3, this.homepage, this.photo, secondary);
    }
}
