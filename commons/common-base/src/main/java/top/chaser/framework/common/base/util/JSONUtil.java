package top.chaser.framework.common.base.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class JSONUtil {
    public static final ObjectMapper MAPPER = new ObjectMapper();
    private static final ObjectMapper UNKNOWN_PROPERTIES_MAPPER = new ObjectMapper();

    static {
        UNKNOWN_PROPERTIES_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String toPrettyString(Object value) {
        return toPrettyString(value, false);
    }

    public static String toPrettyString(Object value, boolean failOnUnknowProperties) {
        if (value == null) {
            return null;
        }
        try {
            if (failOnUnknowProperties) {
                return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(value);
            } else {
                return UNKNOWN_PROPERTIES_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(value);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJSONString(Object value) {
        if (value == null) {
            return null;
        }
        StringWriter writer = new StringWriter(1000);
        try {
            MAPPER.writeValue(writer, value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return writer.toString();
    }

    public static boolean isJson(String jsonStr) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(jsonStr);
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    public static <T> T getByPath(Object json, String expression, Class<T> clazz) {
        JSON parse = cn.hutool.json.JSONUtil.parse(json);
        return Convert.convert(clazz, cn.hutool.json.JSONUtil.getByPath(parse, expression));
    }

    public static Object getByPath(Object json, String expression) {
        JSON parse = cn.hutool.json.JSONUtil.parse(json);
        return cn.hutool.json.JSONUtil.getByPath(parse, expression);
    }

    public static Map parseObject(String value) {
        if (StrUtil.isBlank(value)) {
            return null;
        }
        return parseObject(value, HashMap.class);
    }

    /**
     * 将json字符串转换成bean
     *
     * @param value
     * @param clazz
     * @param failOnUnknowProperties true:如果有未知属性，转换会失败
     * @param <T>
     * @return
     * @Author wangjiajun
     */
    public static <T> T parseObject(String value, Class<T> clazz, boolean failOnUnknowProperties) {
        if (StrUtil.isBlank(value)) {
            return null;
        }
        try {
            if (failOnUnknowProperties) {
                return MAPPER.readValue(value, clazz);
            } else {
                return UNKNOWN_PROPERTIES_MAPPER.readValue(value, clazz);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseObject(String value, Class<T> clazz) {
        return parseObject(value, clazz, true);
    }

    public static <T> List<T> parseArray(String s, Class clazz) {
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(ArrayList.class, clazz);
        try {
            return MAPPER.readValue(s, javaType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
