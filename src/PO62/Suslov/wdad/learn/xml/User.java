package PO62.Suslov.wdad.learn.xml;

import java.util.Objects;

public class User {
    private String mail;
    private String name;
    private String rights;

    public User(String mail, String name) {
        this(mail, name, "R");
    }

    public User(String mail, String name, String rights) {
        this.mail = mail;
        this.name = name;
        this.rights = rights;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }
}
