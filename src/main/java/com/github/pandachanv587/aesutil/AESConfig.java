package com.github.pandachanv587.aesutil;

import com.github.pandachanv587.aesutil.exception.AESConfigParamsError;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
