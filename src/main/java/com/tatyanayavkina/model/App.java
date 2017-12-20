package com.tatyanayavkina.model;

import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.NotEmpty;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.Objects;

@Entity
public class App {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * App name
     */
    @NotEmpty
    @Column(nullable = false)
    private String name;

    /**
     * App belongs to publisher
     */
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    /**
     * Active version of app
     */
    @OneToOne
    @JoinColumn(name = "active_version_id")
    private AppVersion activeVersion;

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

    @Nullable
    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @Nullable
    public AppVersion getActiveVersion() {
        return activeVersion;
    }

    public void setActiveVersion(AppVersion activeVersion) {
        this.activeVersion = activeVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        App app = (App) o;
        return Objects.equals(id, app.id) &&
                Objects.equals(name, app.name) &&
                Objects.equals(publisher, app.publisher) &&
                Objects.equals(activeVersion, app.activeVersion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, publisher, activeVersion);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("publisher", publisher)
                .add("activeVersion", activeVersion)
                .toString();
    }
}
