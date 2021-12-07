package ru.itmo.wp.lesson8.form;

import javax.validation.constraints.*;

public class NoticeCreationForm {
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
