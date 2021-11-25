package ru.itmo.wp.model.domain;

import java.io.Serializable;

public class Article extends AbstractModel implements Serializable {
    private long userId;
    private String text;
    private String title;
    private boolean hidden;

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isHidden() {
        return hidden;
    }

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
