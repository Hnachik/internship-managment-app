package com.bezkoder.spring.security.postgresql.enums;

public enum DateFormatPattern {

    US_DATE_SHORT_FORMAT("MM/dd/yyyy");

    private final String value;

    public String getValue() {
        return value;
    }

    DateFormatPattern(final String value) {
        this.value = value;
    }
}
