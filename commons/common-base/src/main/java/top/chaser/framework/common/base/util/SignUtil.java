package top.chaser.framework.common.base.util;

import cn.hutool.core.codec.Base64;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @program:
 * @description: 签名器
 * @author:
 * @date 2021/4/26 5:25 下午
 **/
@Slf4j
public class SignUtil {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SHA_256_WITH_RSA = "SHA256WithRSA";
    public static final String SHA_256_WITH_RSA_PSS = SHA_256_WITH_RSA+"/PSS";
    public static final String SIGN_FIELD = "sign";

    public static String getSignCheckContent(Map<String, String> params) {
        if (params == null) {
            return null;
        }

        StringBuilder content = new StringBuilder();
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            content.append(i == 0 ? "" : "&").append(key).append("=").append(value);
        }
        return content.toString();
    }

    /**
     * 验证签名 SHA_256_WITH_RSA_PSS
     *
     * @param content      待验签的内容
     * @param sign         签名值的Base64串
     * @param publicKeyStr 公钥
     * @return true：验证成功；false：验证失败
     */
    public static boolean verify(String content, String sign, String publicKeyStr) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            byte[] encodedKey = publicKeyStr.getBytes();
            encodedKey = Base64.decode(encodedKey);
            PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
            Signature signature = Signature.getInstance(SHA_256_WITH_RSA_PSS,new BouncyCastleProvider());
            signature.initVerify(publicKey);
            signature.update(content.getBytes(StandardCharsets.UTF_8));
            return signature.verify(Base64.decode(sign.getBytes()));
        } catch (Exception e) {
            String error = "验签异常，content=" + content + " sign=" + sign + " publicKey=" + publicKeyStr + " reason=" + e.getMessage();
            log.error(error, e);
            throw new RuntimeException(error, e);
        }
    }

    /**
     * 计算签名 SHA_256_WITH_RSA_PSS
     *
     * @param content       待签名的内容
     * @param privateKeyStr 私钥
     * @return 签名值的Base64串
     */
    public static String sign(String content, String privateKeyStr) {
        try {
            byte[] encodedKey = privateKeyStr.getBytes();
            encodedKey = Base64.decode(encodedKey);
            PrivateKey privateKey = KeyFactory.getInstance(KEY_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(encodedKey));
            Signature signature = Signature.getInstance(SHA_256_WITH_RSA_PSS, new BouncyCastleProvider());
            signature.initSign(privateKey);
            signature.update(content.getBytes(StandardCharsets.UTF_8));
            byte[] signed = signature.sign();
            return Base64.encode(signed);
        } catch (Exception e) {
            String error = "签名异常，content=" + content + " privateKeySize=" + privateKeyStr.length() + " reason=" + e.getMessage();
            log.error(error, e);
            throw new RuntimeException(error, e);
        }
    }

    /**
     * 对参数进行验签
     *
     * @param parameters 参数集合
     * @param publicKey  公钥
     * @return true：验证成功；false：验证失败
     */
    public static boolean verifyParams(Map<String, String> parameters, String publicKey) {
        String sign = parameters.get(SIGN_FIELD);
        parameters.remove(SIGN_FIELD);

        String content = getSignCheckContent(parameters);

        return verify(content, sign, publicKey);
    }

}
