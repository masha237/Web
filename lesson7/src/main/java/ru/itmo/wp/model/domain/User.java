package ru.itmo.wp.model.domain;

import java.io.Serializable;
import java.util.Date;

public class User extends AbstractModel implements Serializable {
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
