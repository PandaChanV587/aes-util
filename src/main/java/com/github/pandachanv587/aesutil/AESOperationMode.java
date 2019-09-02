package com.github.pandachanv587.aesutil;

public enum AESOperationMode {

    CBC("CBC"),
    EBC("ECB"),
    DEFAULT("CBC");

    private String value;

    private AESOperationMode(String operationMode) {
        this.value = operationMode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
