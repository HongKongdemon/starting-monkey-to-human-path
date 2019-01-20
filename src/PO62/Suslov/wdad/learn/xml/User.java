package PO62.Suslov.wdad.learn.xml;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String mail;
    private String rights;

    public String getMail() {
        return mail;
    }

    public String getName() {
        return name;
    }

    public String getRights() {
        return rights;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }
}