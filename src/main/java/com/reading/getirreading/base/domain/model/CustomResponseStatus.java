package com.reading.getirreading.base.domain.model;


public enum CustomResponseStatus {
    SUCCESS("success"),
    ERROR("error");

    public final String value;

    private CustomResponseStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}