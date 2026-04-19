package com.ds.sqs.taxation.api.filter;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SqlQueryCollector {

    private static final ThreadLocal<List<Map<String, Object>>> queries = ThreadLocal.withInitial(ArrayList::new);

    public static void add(String query, List<Object> params, long durationMs) {
        Map<String, Object> q = new HashMap<>();
        q.put("query", query);
        q.put("params", params);
        q.put("durationMs", durationMs);
        queries.get().add(q);
    }

    public static List<Map<String,Object>> get() {
        return new ArrayList<>(queries.get());
    }

    public static void clear() {
        queries.get().clear();
    }
}