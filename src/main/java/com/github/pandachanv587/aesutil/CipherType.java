package com.github.pandachanv587.aesutil;

public enum CipherType {

    ENCRYPT(1),
    DECRYPT(2);

    private Integer value;

    private CipherType(Integer cipherType) {
        this.value = cipherType;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
