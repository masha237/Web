package ru.itmo.wp.lesson8.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(
        indexes = @Index(columnList = "creationTime")
)
public class Notice {
    @Id
    @GeneratedValue
    private long id;

    @CreationTimestamp
    private Date creationTime;


    @Lob
    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}
