package ru.itmo.wp.model.domain;

import java.io.Serializable;

public class Event extends AbstractModel implements Serializable {
    private long userId;
    private Type type;

    public long getUserId() {
        return userId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public enum Type {
        ENTER, LOGOUT
    }
}
