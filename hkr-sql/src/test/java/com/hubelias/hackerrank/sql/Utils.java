package com.hubelias.hackerrank.sql;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Utils {
    static String camelCaseToKebabCase(String text) {
        return Arrays
                .stream(text.split("(?=[A-Z])"))
                .map(String::toLowerCase)
                .collect(Collectors.joining("-"));
    }
}
