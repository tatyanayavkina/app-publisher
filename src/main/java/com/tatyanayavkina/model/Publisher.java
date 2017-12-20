package com.tatyanayavkina.model;

import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Publisher {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * Name of publisher
     */
    @NotEmpty
    @Column(nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Publisher publisher = (Publisher) o;
        return Objects.equals(id, publisher.id) &&
                Objects.equals(name, publisher.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .toString();
    }
}
