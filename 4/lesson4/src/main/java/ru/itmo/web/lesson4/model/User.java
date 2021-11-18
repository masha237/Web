package ru.itmo.web.lesson4.model;

import java.awt.*;

public class User {

    public enum DataColor {
        RED,
        GREEN,
        BLUE
    }


    private final long id;
    private final String handle;
    private final String name;
    private final DataColor color;

    public User(long id, String handle, String name, DataColor color) {
        this.id = id;
        this.handle = handle;
        this.name = name;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public String getHandle() {
        return handle;
    }

    public String getName() {
        return name;
    }

    public DataColor getColor() {
        return color;
    }


}
