package com.tencent.wxcloudrun.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : gs
 * @date :2022/8/24
 */
public class RequestUtil {

    /**
     * 获取request
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request;
    }

    /**
     * 受限资源--获取request中的属性值
     * @param key
     * @return
     */
    public static Object getAttributeFromRequest(String key){
        HttpServletRequest request = getRequest();
        Object attribute = request.getAttribute(key);
        return attribute;
    }

    /**
     * 获取请求的前缀
     * @param req
     * @return
     */
    public static String getPathContext(HttpServletRequest req) {
        String servletPath = req.getServletPath();
        String[] pathParams=servletPath.split("/");
        return pathParams[1];
    }

    /**
     * 获取heard中的参数
     * @return
     */
    public static Map<String, String> getRequestHeaderMap() {
        HttpServletRequest request = getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> headerMap = new HashMap<>();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            headerMap.put(name, request.getHeader(name));
        }
        return headerMap;
    }

}

