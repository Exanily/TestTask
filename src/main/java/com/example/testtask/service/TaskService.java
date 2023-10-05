package com.example.testtask.service;

import com.example.testtask.exception.RequestLimitException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final Integer serverLimitRequest;
    private final Integer serverLimitResponse;

    public TaskService(@Value("${server.limit.request:}") String serverLimitRequest,
                       @Value("${server.limit.response:}") String serverLimitResponse) {
        this.serverLimitRequest = serverLimitRequest.isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(serverLimitRequest);
        this.serverLimitResponse = serverLimitResponse.isEmpty() ? Integer.MAX_VALUE : Integer.parseInt(serverLimitResponse);
    }

    public String getCountCharsToString(String str) {
        if (str.length() > serverLimitRequest) {
            throw new RequestLimitException("Error: The length of the string is more than " + serverLimitRequest + " characters");
        }
        if (str.isEmpty()) {
            return str;
        }
        Map<Character, Integer> map = new HashMap<>();
        strToMap(map, str);

        Map<Character, Integer> newMap = sortedMap(map);

        StringBuilder builder = new StringBuilder();
        mapToStringFormat(builder, newMap);
        String result = builder.toString();
        return result.substring(0, result.length() - 2);
    }

    private void strToMap(Map<Character, Integer> map, String str) {
        str.chars().forEach(valueChar -> {
            char c = (char) valueChar;
            int count = map.getOrDefault(c, 0);
            count++;
            map.put(c, count);
        });
    }

    private Map<Character, Integer> sortedMap(Map<Character, Integer> map) {
        return map.entrySet()
                .stream()
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .limit(serverLimitResponse)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    private void mapToStringFormat(StringBuilder builder, Map<Character, Integer> map) {
        map.forEach((key, value) -> builder.append("\"").append(key).append("\":").append(value).append(", "));
    }


}
