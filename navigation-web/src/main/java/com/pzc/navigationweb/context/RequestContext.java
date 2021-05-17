package com.pzc.navigationweb.context;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class RequestContext implements Serializable {
    private static final ThreadLocal<RequestContext> contexts = new ThreadLocal();
    private final ConcurrentMap<String, Object> contextMap = new ConcurrentHashMap();

    public RequestContext() {
        contexts.set(this);
    }

    public RequestContext(Map<String, Object> initMap) {
        if (MapUtils.isNotEmpty(initMap)) {
            this.contextMap.putAll(initMap);
        }

        contexts.set(this);
    }

    public static RequestContext get() {
        return (RequestContext)contexts.get();
    }

    public static void destroy() {
        contexts.remove();
    }

    public Object get(String key) {
        return this.contextMap.get(key);
    }

    public void put(String key, Object val) {
        this.contextMap.put(key, val);
    }

    public void remove(String key) {
        this.contextMap.remove(key);
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
