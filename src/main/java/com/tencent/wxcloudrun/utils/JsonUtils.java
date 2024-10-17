package com.tencent.wxcloudrun.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang.StringUtils;

import java.util.Optional;

/**
 * Json工具类
 *
 * @author wangyongliang
 * @since 2023-12-25
 */
public class JsonUtils {

    /**
     * 对象转Json字符串
     *
     * @param value 对象
     * @return Json字符串
     */
    public static <T> String toJson(T value) {
        return JSON.toJSONString(value, SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 对象转格式化Json字符串
     *
     * @param value 对象
     * @return 格式化Json字符串
     */
    public static <T> String toPrettyJson(T value) {
        return JSON.toJSONString(value, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.PrettyFormat);
    }

    /**
     * json字符串转换对象
     *
     * @param json  json字符串
     * @param klass 对象类型
     * @return 对象
     */
    public static <T> T fromJson(String json, Class<T> klass) {
        return JSON.parseObject(json, klass);
    }

    /**
     * json字符串转换对象
     *
     * @param json json字符串
     * @param type 对象类型
     * @return 对象
     */
    public static <T> T fromJson(String json, TypeReference<T> type) {
        return JSON.parseObject(json, type);
    }

    /**
     * json字符串转换对象
     *
     * @param json  json字符串
     * @param klass 对象类型
     * @return 对象
     */
    public static <T> Optional<T> fromJsonWithoutException(String json, Class<T> klass) {
        if (StringUtils.isEmpty(json)) {
            return Optional.empty();
        }
        try {
            return Optional.of(fromJson(json, klass));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * json字符串转换对象
     *
     * @param json json字符串
     * @param type 对象类型
     * @return 对象
     */
    public static <T> Optional<T> fromJsonWithoutException(String json, TypeReference<T> type) {
        if (StringUtils.isEmpty(json)) {
            return Optional.empty();
        }
        try {
            return Optional.of(fromJson(json, type));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
