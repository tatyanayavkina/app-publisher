package com.tatyanayavkina.model;

import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Objects;

/**
 * Person who is responsible for AppVersion
 */
@Entity
public class ReleaseManager {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * Name of person
     */
    @NotEmpty
    @Column(nullable = false)
    private String name;

    /**
     * Email
     */
    @Column
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReleaseManager that = (ReleaseManager) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("email", email)
                .toString();
    }
}
