package com.socks.tests;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;

import io.restassured.RestAssured;

import java.util.Locale;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.socks.api.conditions.Conditions;
import com.socks.api.payloads.UserPayload;
import com.socks.api.services.UserApiService;

public class UsersTestWithServices {

    private final UserApiService userApiService = new UserApiService();
    private final Faker faker = new Faker(new Locale("ja"));

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "http://192.168.88.49/";
    }

//    @Test
    public void testCanRegisterNewUser() {
        // given
        UserPayload user = new UserPayload()
                .username(faker.name().username())
                .email("test@test.com")
                .password("test123");

        // expect
        userApiService.registerUser(user)
                .shouldHave(Conditions.statusCode(HttpStatus.SC_OK))
                .shouldHave(Conditions.bodyField("id", not(isEmptyOrNullString())));
    }

//    @Test
    public void testCanNotRegisterSameUserTwice() {
        UserPayload user = new UserPayload()
                .username(faker.name().username())
                .email("test@test.com")
                .password("test123");

        userApiService.registerUser(user)
                .shouldHave(Conditions.statusCode(HttpStatus.SC_OK))
                .shouldHave(Conditions.bodyField("id", not(isEmptyOrNullString())));

        userApiService.registerUser(user)
                .shouldHave(Conditions.statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR));
    }
}
