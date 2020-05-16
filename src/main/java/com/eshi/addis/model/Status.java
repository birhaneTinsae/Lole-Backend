package com.eshi.addis.model;

public enum Status {
    ACTIVE('A'), PENDING('P'),  DELETED('D');
    private final char status;

    Status(char status) {
        this.status = status;
    }

    public char getStatus() {
        return status;
    }

}
