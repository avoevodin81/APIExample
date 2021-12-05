package com.socks.api.services;

import io.qameta.allure.Step;

import com.socks.api.assertions.AssertableResponse;
import com.socks.api.payloads.UserPayload;

public class UserApiService extends ApiService {

    @Step
    public AssertableResponse registerUser(UserPayload user) {
        return new AssertableResponse(setup()
                .body(user)
                .when()
                .post("register"));
    }
}
