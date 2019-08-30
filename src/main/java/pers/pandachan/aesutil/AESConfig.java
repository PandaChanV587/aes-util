package pers.pandachan.aesutil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.pandachan.aesutil.exception.AESConfigParamsError;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AESConfig {

    private String key;
    private AESLevel level = AESLevel.DEFAULT;
    private AESOperationMode operationMode = AESOperationMode.DEFAULT;
    private AESPaddingMode paddingMode = AESPaddingMode.DEFAULT;

    public AESConfig(String key) throws AESConfigParamsError {
        this.key = key;
        check();
    }

    public void check() throws AESConfigParamsError {
        if (key == null || key.trim().isEmpty()) {
            throw new AESConfigParamsError("AES key is required!");
        }
    }

}
