package com.tatyanayavkina.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

/**
 * Request for making app version active
 */
public class MakeVersionActiveRequest {

    /**
     * App version id
     */
    private final long appVersionId;

    @JsonCreator
    public MakeVersionActiveRequest(@JsonProperty("appVersionId") long appVersionId) {
        this.appVersionId = appVersionId;
    }

    public long getAppVersionId() {
        return appVersionId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("appVersionId", appVersionId)
                .toString();
    }
}
