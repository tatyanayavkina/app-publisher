package com.tatyanayavkina.model;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AppValidationRule {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String path;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ValidationType type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ValidationType getType() {
        return type;
    }

    public void setType(ValidationType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AppValidationRule that = (AppValidationRule) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(path, that.path) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path, type);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("path", path)
                .add("type", type)
                .toString();
    }
}
