package pers.pandachan.aesutil;

public enum AESPaddingMode {

    PKCS5padding("PKCS5padding"),
    PKCS7Padding("PKCS7Padding"),
    DEFAULT("PKCS7Padding");

    private String value;

    private AESPaddingMode(String paddingMode) {
        this.value = paddingMode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
