package com.cedraz.exams.app.exception;

public enum ExceptionType {

    ENTITY_NOT_FOUND("not.found"),
    DUPLICATE_ENTITY("duplicate"),
    ENTITY_EXCEPTION("exception");

    String value;
    String getValue() {
        return this.value;
    }
    ExceptionType(String value) {
        this.value = value;
    }

}