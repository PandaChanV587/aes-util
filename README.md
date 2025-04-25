# AES-Util
AES加密/解密工具

支持的加密位数：128/256
支持的加密模式：CBC/ECB
支持的填充模式：PKCS5padding/PKCS7Padding

## 例子

```java
    import pers.pandachan.aesutil.*;

    AESUtil aesUtil = new AESUtil("key-xxxxxxxx");
    String text = "HelloWorld!";
    String encryptText = aesUtil.encryptToBase64String(text);
    String decryptText = aesUtil.decryptBase64String(encryptText);
```
默认为 CBC PKCS5padding 128位 

2025-04-05
