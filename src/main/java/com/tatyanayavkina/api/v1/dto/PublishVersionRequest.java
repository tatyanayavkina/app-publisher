package com.tatyanayavkina.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Request for app version publishing
 */
public class PublishVersionRequest {
    /**
     * App id
     */
    private final long appId;

    /**
     * Version
     */
    @NotEmpty
    private final String version; //todo: add validator for unique

    /**
     * Id of release manager
     */
    private final long releaseManagerId;

    @JsonCreator
    public PublishVersionRequest(@JsonProperty("appId") long appId,
                                 @JsonProperty("version") String version,
                                 @JsonProperty("releaseManagerId")long releaseManagerId) {
        this.appId = appId;
        this.version = version;
        this.releaseManagerId = releaseManagerId;
    }

    public long getAppId() {
        return appId;
    }

    public String getVersion() {
        return version;
    }

    public long getReleaseManagerId() {
        return releaseManagerId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("appId", appId)
                .add("version", version)
                .add("releaseManagerId", releaseManagerId)
                .toString();
    }
}
