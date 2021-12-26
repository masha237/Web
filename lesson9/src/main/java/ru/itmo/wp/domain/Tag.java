package ru.itmo.wp.domain;

import org.apache.logging.log4j.util.PropertySource;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Comparator;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = "name")
)
public class Tag implements Comparable<Tag>{
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 60)
    private String name;

    /** @noinspection unused*/
    public Tag() {
    }

    public Tag(@NotNull String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Tag tag) {
        return Long.compare(id, tag.id);
    }
}
