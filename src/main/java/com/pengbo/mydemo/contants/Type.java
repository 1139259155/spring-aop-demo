package com.pengbo.mydemo.contants;


public enum Type {

    before("before"),
    after("after");

    private final String value;

    Type(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }
}
