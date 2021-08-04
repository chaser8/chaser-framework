package top.chaser.framework.common.base.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class RsaUtilTest {

    @Test
    void generateKeyPair() {
        Map<String, Object> stringObjectMap = RsaUtil.generateKeyPair();
        String privateKeyStr = RsaUtil.getPrivateKey(stringObjectMap);
        String publicKeyStr = RsaUtil.getPublicKey(stringObjectMap);
        System.out.println(StrUtil.format("privateKey:{}",privateKeyStr));
        System.out.println(StrUtil.format("publicKey:{}",publicKeyStr));
    }
    @Test
    void decrypt() {
        Map<String, Object> stringObjectMap = RsaUtil.generateKeyPair();
        String privateKeyStr = RsaUtil.getPrivateKey(stringObjectMap);
        String publicKeyStr = RsaUtil.getPublicKey(stringObjectMap);
        String src = "我们都是好便宜";
        String encrypt = RsaUtil.encrypt(src, publicKeyStr);
        String decrypt = RsaUtil.decrypt(encrypt, privateKeyStr);
        System.out.println(StrUtil.format("encrypt:{}",encrypt));
        System.out.println(StrUtil.format("decrypt:{}",decrypt));
        assertEquals(src,decrypt);
    }
}
