package ru.itmo.wp.lesson8.form;


import ru.itmo.wp.lesson8.domain.Notice;

import javax.validation.constraints.*;

public class NoticeCreationForm {
    @NotNull
    @NotBlank
    @Size(max = 65000)
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
