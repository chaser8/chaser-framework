package top.chaser.framework.common.base.util;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SignUtilTest {

    @Test
    void getSignCheckContent() {
    }

    @Test
    void verify() {

    }

    @Test
    void sign() {
        Map<String, Object> stringObjectMap = RsaUtil.generateKeyPair();
        String privateKeyStr = RsaUtil.getPrivateKey(stringObjectMap);
        String publicKeyStr = RsaUtil.getPublicKey(stringObjectMap);
        String sign = SignUtil.sign("我们哈哈", privateKeyStr);

        boolean verify = SignUtil.verify("我们哈哈", sign, publicKeyStr);
        assertEquals(verify,true);
    }

    @Test
    void verifyParams() {
    }
}
