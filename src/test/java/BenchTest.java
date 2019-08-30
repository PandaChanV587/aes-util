import org.junit.Test;
import pers.pandachan.aesutil.*;
import pers.pandachan.aesutil.exception.AESConfigParamsError;
import pers.pandachan.aesutil.exception.CipherCreateError;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

public class BenchTest {

    @Test
    public void text() throws AESConfigParamsError, InvalidAlgorithmParameterException, InvalidKeyException, CipherCreateError, BadPaddingException, IllegalBlockSizeException {
        String password = "12312312321231r3982yy2837y19e312313123123123";
        String text = "你好啊。。。。。。。。。。。。。。。。。。。。。。！！！！";

        AESConfig aesConfig = AESConfig.builder()
                .key(password)
                .level(AESLevel.LEVEL_128)
                .operationMode(AESOperationMode.CBC)
                .paddingMode(AESPaddingMode.PKCS5padding)
                .build();

        AESUtil aesTest = new AESUtil(aesConfig);

        int count = 1000000;

        String encodeText = null;

        System.out.println(aesTest.encryptToBase64(text));
        System.out.println(aesTest.decryptBase64String(aesTest.encryptToBase64(text)));

        try {
            long t = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
//                encodeText = aesTest.encryptToBase64(text);
            }
            long t2 = System.currentTimeMillis();
            System.out.println("加密" + count + "次：" + (t2 - t));

            t = System.currentTimeMillis();
            for (int i = 0; i < count; i++) {
                aesTest.decryptBase64String(encodeText);
            }
            t2 = System.currentTimeMillis();
            System.out.println("解密" + count + "次：" + (t2 - t));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void asd() throws InvalidKeyException, InvalidAlgorithmParameterException, AESConfigParamsError, CipherCreateError, BadPaddingException, IllegalBlockSizeException {
        AESUtil aesUtil = new AESUtil("key-xxxxxxxx");
        String text = "HelloWorld!";
        String encryptText = aesUtil.encryptToBase64String(text);
        String decryptText = aesUtil.decryptBase64String(encryptText);
    }

}
