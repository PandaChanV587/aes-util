package pers.pandachan.aesutil.util;

import pers.pandachan.aesutil.AESConfig;
import pers.pandachan.aesutil.AESOperationMode;
import pers.pandachan.aesutil.AESPaddingMode;
import pers.pandachan.aesutil.CipherType;
import pers.pandachan.aesutil.exception.CipherCreateError;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Security;

public class CipherUtil {

    private static Boolean addBouncyCastleProviderFlag = false;

    private static final String AES_INSTANCE = "AES/";
    private static final String FORWARD_SLASH = "/";

    private static void addBouncyCastleProvider() {
        if (!addBouncyCastleProviderFlag) {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            addBouncyCastleProviderFlag = true;
        }
    }

    public static Cipher createCipher(CipherType cipherType, AESConfig aesConfig) throws InvalidAlgorithmParameterException,
            InvalidKeyException, CipherCreateError {
        Cipher cipher;
        addBouncyCastleProvider();

        try {
            cipher = Cipher.getInstance(getInstance(aesConfig.getOperationMode(), aesConfig.getPaddingMode()));
        } catch (Exception ignored) {
            // 这里应该永远不会被触发
            throw new CipherCreateError("");
        }
        SecretKeySpec secretKeySpec = KeyUtil.createSecretKeySpec(aesConfig);

        if (aesConfig.getOperationMode() == AESOperationMode.CBC) {
            cipher.init(cipherType.getValue(), secretKeySpec, createIvParameterSpec());
        } else {
            cipher.init(cipherType.getValue(), secretKeySpec);
        }
        return cipher;
    }

    /**
     * 创建Cipher对应的Instance
     * 参数可传空
     * 默认加密模式 {@link AESOperationMode#DEFAULT}
     * 默认填充模式 {@link AESPaddingMode#DEFAULT}
     *
     * @param operationMode 加密模式
     * @param paddingMode   填充模式
     * @return Instance
     */
    public static String getInstance(AESOperationMode operationMode, AESPaddingMode paddingMode) {
        return AES_INSTANCE + operationMode + FORWARD_SLASH + paddingMode;
    }

    /**
     * 创建用于CBC加密模式的向量Byte数组
     *
     * @return IvParameterSpec
     */
    private static IvParameterSpec createIvParameterSpec() {
        byte[] bytes = new byte[16];
        return new IvParameterSpec(bytes);
    }

}
