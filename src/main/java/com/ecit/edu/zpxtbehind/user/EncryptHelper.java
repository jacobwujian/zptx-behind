package com.ecit.edu.zpxtbehind.user;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Random;
import java.util.UUID;


enum EncryptType {
    MD5("MD5"),
    SHA1("SHA1"),
    SHA224("SHA-224"),
    SHA256("SHA-256"),
    SHA384("SHA-384"),
    SHA512("SHA-512");

    private String algorithmName;

    private EncryptType(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

}

public final class EncryptHelper {


    public static final Charset DEFAULT_CHARSET = Charset.defaultCharset();
    public static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    public static final Charset GBK_CHARSET = Charset.forName("GBK");

    private static final String key1 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgtbNRmDB/Jux2FEl4aDo09UzZqxslSyqMV/9fa9aZA+fR8IW9OgDthq4SkL3bx8fNG0Fro7vH0GENrqHqXX2HRi1mhETcjuh20BN4zKpCKwvzOw88FKTpqOSQT6PgJbi0ACJKdPV7o/MXt5aYKu7CBB+EnoBYa9XSjzMQ/vcQoQIDAQAB";
    private static final String key2 = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKC1s1GYMH8m7HYUSXhoOjT1TNmrGyVLKoxX/19r1pkD59Hwhb06AO2GrhKQvdvHx80bQWuju8fQYQ2uoepdfYdGLWaERNyO6HbQE3jMqkIrC/M7DzwUpOmo5JBPo+AluLQAIkp09Xuj8xe3lpgq7sIEH4SegFhr1dKPMxD+9xChAgMBAAECgYBzLCR5ij7fMQ7vRHSuxMtKHq3XpKdMn8S7VWkfglIF0OW27894n/QARa/EgFSACHmpBSpqMKonda/INWm2Yw/M2ao+cxthfVXI0pq91DjSkhd+qxmnwozJ/asNYnQU/uKeQSLZ4iZITYbOmxehT+S5feGbio9vd4dy514zwZuDQQJBANsBsyKVs+jATNK493JUYD/e6h0XKwM/l8nJY1lBCZAAbArzsz+Nc5nKfo49e9nTRe2BAaFti6+1Y24KITkdjlkCQQC72x+TJOqguJrhkCiyz+Vm7qeGkKAR2F5ux3sYNGmncSTiXaa+MG1QQ5mag01BVqY2iDjCyjgME7tUlgkt5puJAkBtMpLECsqyobdsViejDOkUz6wBpzyeGQYw6cYsiZ4f++XjLBHS/U+HfgzOFV/8gzhZrLpIaBt/F70k784S8Wa5AkBxXRmf1W5V0TF+H13AEp9wT2L/XLWML1NFbNIG2FJN/oRwqm1eZqEBB9phGQ18+YpSbEA8pIXQG/Q3ZldhwMFpAkEAiydH95gWi43GmeZQiH2JVlxfBDa8gvT+RMdFfnqZDv6lJQGXAVJe61LPrB1VF+M4NUwxgsfa2GO3Q0R9iIgyAg==";

    /**
     * 根据指定的算法名称获取对应的 "信息摘要" 对象
     *
     * @param algorithmName 指定的算法名称
     * @return
     */
    public static final MessageDigest digest(String algorithmName) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithmName);
            return digest;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("不支持[ " + algorithmName + " ]算法", e);
        }
    }

    /**
     * 使用指定的 加密算法 对字符串进行加密处理
     *
     * @param source 需要加密的字符串
     * @return 返回采用指定加密算法加密后的字符串
     */
    public static final String encrypt(String source) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        if (StringHelper.empty(source)) {
            throw new RuntimeException("被加密的字符串不能为空");
        }
        byte[] decoded = Base64.decodeBase64(key1);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(source.getBytes(StandardCharsets.UTF_8)));
        // 使用 信息摘要对象 对字节序列 进行加密处理
        return outStr;
    }

    public static final String unencrypt(String source) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
        byte[] inputByte = Base64.decodeBase64(source.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(key2);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }
}

final class StringHelper {

    private static Random random = new SecureRandom();

    /**
     * 随机产生一个含有 n 个字符的字符串( 这些字符可以是 a ~ z 、A ~ Z、0 ~ 1  之间的任意字符 )
     *
     * @param n 随机产生的字符串的字符个数
     * @return 返回一个含有n个字符的字符串
     */
    public static String random(final int n) {
        final char begin = 'a';
        final char end = 'z';
        final int length = end - begin;
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int offset = random.nextInt(length);
            char ch = (char) (begin + offset);
            buffer.append(ch);
        }

        return buffer.toString();
    }

    /**
     * 判断一个字符串是否为 null 或 空串 或 空白
     *
     * @param source 需要判断的字符串
     * @return 当字符串为 null 或 为 空白、空串 时返回 true
     */
    public static final boolean empty(String source) {
        return source == null || source.trim().isEmpty();
    }

    /**
     * 判断一个字符串是否不是null且不是空串、不是空白
     *
     * @param source 需要判断的字符串
     * @return 当 字符串是不是null且不是空串也不是空白时返回 true
     */
    public static final boolean notEmpty(String source) {
        return source != null && source.trim().length() > 0;
    }

    /**
     * 判断一个字符串变量是否为 null
     *
     * @param source 需要判断的字符串
     * @return 当 字符串变量 为 null 时返回 true
     */
    public static final boolean isNull(String source) {
        return source == null;
    }

    /**
     * 判断一个字符串是否为 空串
     *
     * @param source 需要判断的字符串
     * @return 当字符串中的值是 空串 或 空白 串时返回 true
     */
    public static final boolean emptyString(String source) {
        return (source != null) && source.length() == source.trim().length();
    }

    /**
     * 判断一个字符串是否为 空白 串
     *
     * @param source 需要判断的字符串
     * @return 当字符串中的值是 空白 串时返回 true
     */
    public static final boolean blank(String source) {
        return (source != null) && source.length() > source.trim().length() && source.trim().isEmpty();
    }

    /**
     * 比较两个非空(不是null，不是空串、不是空白)字符串是否"相等"
     *
     * @param one      第一个需要比较的字符串
     * @param theOther 另一个参与比较的字符串
     * @return 当 两个字符串 都不为空串 且 内容完全一致 (剔除首尾空白后、大小写也一致)时返回 true
     */
    public static final boolean equals(String one, String theOther) {
        return equals(one, theOther, true, false);
    }

    /**
     * 比较两个字符串是否 "相等"
     *
     * @param one         参与比较的第一个字符串
     * @param theOther    参与比较的另一个字符串
     * @param escapeSpace 是否需要剔除首尾空白 ( true 表示需要剔除首尾空白，false 表示不剔除 )
     * @param ignoreCase  是否忽略大小写 ( true 表示忽略大小写 ，false 表示不忽略大小写 )
     * @return
     */
    public static final boolean equals(String one, String theOther, boolean escapeSpace, boolean ignoreCase) {

        if (one == null || theOther == null) {
            return false;
        }

        if (escapeSpace) {
            one = one.trim();
            theOther = theOther.trim();
        }

        return ignoreCase ? one.equalsIgnoreCase(theOther) : one.equals(theOther);
    }

    public static final String uuid() {
        // 随机产生一个 UUID 实例
        UUID uuid = UUID.randomUUID();
        // 获得 UUID 实例中的字符串 ( 是一个包含了 4 个 - 好的36位长度的字符串 )
        String uuidString = uuid.toString();
        // 剔除 字符串中的 - 字符 (用空串替换)
        uuidString = uuidString.replace("-", "");
        // 将字符串全部转换成大写字母
        uuidString = uuidString.toLowerCase();
        return uuidString;
    }


    /**
     * 将字符序列中的所有字符全部转大写
     *
     * @param cs 需要转换的字符序列，可以是 String 、StringBuffer 、StringBuilder 等
     * @return 返回转换为大写后的字符串
     */
    public static final String upperCase(CharSequence cs) {

        String result = "";

        if (cs != null) {

            Class<?> type = cs.getClass();

            if (type == String.class) {
                String s = (String) cs;
                result = s.toUpperCase();
            } else if (type == StringBuffer.class) {
                StringBuffer buffer = (StringBuffer) cs;
                for (int i = 0, n = buffer.length(); i < n; i++) {
                    char ch = buffer.charAt(i);
                    if (isLowerCase(ch)) {
                        buffer.deleteCharAt(i);
                        buffer.insert(i, (char) (ch - 32));
                    }
                }
                result = buffer.toString();
            } else if (type == StringBuilder.class) {
                StringBuilder builder = (StringBuilder) cs;
                for (int i = 0, n = builder.length(); i < n; i++) {
                    char ch = builder.charAt(i);
                    if (isLowerCase(ch)) {
                        builder.deleteCharAt(i);
                        builder.insert(i, (char) (ch - 32));
                    }
                }
                result = builder.toString();
            }
        }

        return result;

    }

    /**
     * 判断指定字符是否是小写字符
     *
     * @param c 需要判断的字符
     * @return 如果被判断的字符是小写字符就返回 true
     */
    public static final boolean isLowerCase(char c) {
        if (c >= 'a' && c <= 'z') {
            return true;
        } else {
            return false;
        }
    }

}
