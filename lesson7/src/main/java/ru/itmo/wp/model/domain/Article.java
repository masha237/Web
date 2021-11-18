package ru.itmo.wp.model.domain;

import java.io.Serializable;
import java.util.Date;

public class Article extends AbstractModel implements Serializable {
    private long userId;
    private String text, title;

    public long getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
