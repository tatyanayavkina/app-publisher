package com.tatyanayavkina.model;

import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class AppVersion {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "app_id", nullable = false)
    private App app;

    /**
     * Version of app, e.g. "v15-rc"
     */
    @NotEmpty
    @Column(nullable = false)
    private String version;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "release_manager_id", nullable = false)
    private ReleaseManager releaseManager;

    protected AppVersion() {
    }

    public AppVersion(App app, String version, ReleaseManager releaseManager) {
        this.app = app;
        this.version = version;
        this.releaseManager = releaseManager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ReleaseManager getReleaseManager() {
        return releaseManager;
    }

    public void setReleaseManager(ReleaseManager releaseManager) {
        this.releaseManager = releaseManager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AppVersion that = (AppVersion) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(app, that.app) &&
                Objects.equals(version, that.version) &&
                Objects.equals(releaseManager, that.releaseManager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, app, version, releaseManager);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("app", app)
                .add("version", version)
                .add("releaseManager", releaseManager)
                .toString();
    }
}
