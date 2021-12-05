package com.socks.api.conditions;

import lombok.experimental.UtilityClass;

import org.hamcrest.Matcher;

@UtilityClass
public class Conditions {
    public static StatusCodeCondition statusCode(int code) {
        return new StatusCodeCondition(code);
    }

    public static BodyFieldCondition bodyField(String jsonPath, Matcher matcher) {
        return new BodyFieldCondition(jsonPath, matcher);
    }
}
