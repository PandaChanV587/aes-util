package com.github.pandachanv587.aesutil;

import com.github.pandachanv587.aesutil.exception.AESConfigParamsError;
import org.apache.commons.codec.binary.Base64;
import com.github.pandachanv587.aesutil.exception.CipherCreateError;
import com.github.pandachanv587.aesutil.util.CipherUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

public class AESUtil {

    private Cipher encryptCipher;
    private Cipher decryptCipher;

    /**
     * 创建一个 AESUtil
     * 默认位数请查看 {@link AESLevel#DEFAULT}
     * 默认加密模式 {@link AESOperationMode#DEFAULT}
     * 默认填充模式 {@link AESPaddingMode#DEFAULT}
     *
     * @param key 密钥
     */
    public AESUtil(String key) throws AESConfigParamsError, InvalidAlgorithmParameterException, InvalidKeyException, CipherCreateError {
        init(new AESConfig(key));
    }

    /**
     * 创建一个 AESUtil
     *
     * @param aesConfig AES配置
     */
    public AESUtil(AESConfig aesConfig) throws AESConfigParamsError, InvalidAlgorithmParameterException, InvalidKeyException, CipherCreateError {
        init(aesConfig);
    }

    private void init(AESConfig aesConfig) throws AESConfigParamsError, InvalidAlgorithmParameterException, InvalidKeyException, CipherCreateError {
        aesConfig.check();
        encryptCipher = CipherUtil.createCipher(CipherType.ENCRYPT, aesConfig);
        decryptCipher = CipherUtil.createCipher(CipherType.DECRYPT, aesConfig);
    }

    /**
     * 将文本进行加密和Base64编码
     *
     * @param text 文本
     * @return 加密后的Base64字节数组
     */
    public byte[] encryptToBase64(String text) throws BadPaddingException, IllegalBlockSizeException {
        return Base64.encodeBase64(encrypt(text));
    }

    /**
     * 将字节数组进行加密和Base64编码
     *
     * @param bytes 字节数组
     * @return 加密后的Base64字节数组
     */
    public byte[] encryptToBase64(byte[] bytes) throws BadPaddingException, IllegalBlockSizeException {
        return Base64.encodeBase64(encrypt(bytes));
    }

    /**
     * 将文本进行加密和Base64编码
     *
     * @param text 文本
     * @return 加密后的Base64字符串
     */
    public String encryptToBase64String(String text) throws BadPaddingException, IllegalBlockSizeException {
        return Base64.encodeBase64String(encrypt(text));
    }

    /**
     * 将字节数组进行加密和Base64编码
     *
     * @param bytes 字节数组
     * @return 加密后的Base64字符串
     */
    public String encryptToBase64String(byte[] bytes) throws BadPaddingException, IllegalBlockSizeException {
        return Base64.encodeBase64String(encrypt(bytes));
    }

    /**
     * 将文本进行加密
     *
     * @param text 文本
     * @return 加密后的字节数组
     */
    public byte[] encrypt(String text) throws BadPaddingException, IllegalBlockSizeException {
        return encrypt(text.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 将字节数组进行加密
     *
     * @param bytes 字节数组
     * @return 加密后的字节数组
     */
    public synchronized byte[] encrypt(byte[] bytes) throws BadPaddingException, IllegalBlockSizeException {
        return encryptCipher.doFinal(bytes);
    }

    /**
     * 将Base64编码的文本解码并解密
     *
     * @param base64Text Base64文本
     * @return 解密后的文本
     */
    public String decryptBase64String(String base64Text) throws BadPaddingException, IllegalBlockSizeException {
        return decryptToString(Base64.decodeBase64(base64Text));
    }

    /**
     * 将Base64编码的字节数组解码并解密
     *
     * @param base64Bytes Base64字节数组
     * @return 解密后的文本
     */
    public String decryptBase64String(byte[] base64Bytes) throws BadPaddingException, IllegalBlockSizeException {
        return decryptToString(Base64.decodeBase64(base64Bytes));
    }

    /**
     * 将Base64编码的文本解码并解密
     *
     * @param base64Text Base64文本
     * @return 解密后的字节数组
     */
    public byte[] decryptBase64(String base64Text) throws BadPaddingException, IllegalBlockSizeException {
        return decrypt(Base64.decodeBase64(base64Text));
    }

    /**
     * 将Base64编码的字节数组解码并解密
     *
     * @param base64Bytes Base64字节数组
     * @return 解密后的字节数组
     */
    public byte[] decryptBase64(byte[] base64Bytes) throws BadPaddingException, IllegalBlockSizeException {
        return decrypt(Base64.decodeBase64(base64Bytes));
    }

    /**
     * 将加密的字节数组进行解密
     *
     * @param bytes 字节数组
     * @return 解密后的文本
     */
    public String decryptToString(byte[] bytes) throws BadPaddingException, IllegalBlockSizeException {
        return new String(decrypt(bytes), StandardCharsets.UTF_8);
    }

    /**
     * 将加密的字节数组进行解密
     *
     * @param bytes 字节数组
     * @return 解密后的字节数组
     */
    public synchronized byte[] decrypt(byte[] bytes) throws BadPaddingException, IllegalBlockSizeException {
        return decryptCipher.doFinal(bytes);
    }

}
