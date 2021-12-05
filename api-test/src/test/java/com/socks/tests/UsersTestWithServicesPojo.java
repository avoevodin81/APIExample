package com.socks.tests;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;

import io.restassured.RestAssured;

import java.util.Locale;

import org.aeonbits.owner.ConfigFactory;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.socks.api.ProjectConfig;
import com.socks.api.conditions.Conditions;
import com.socks.api.payloads.UserPayload;
import com.socks.api.responses.UserRegistrationResponse;
import com.socks.api.services.UserApiService;

public class UsersTestWithServicesPojo {

    private final UserApiService userApiService = new UserApiService();
    private Faker faker;

    @BeforeMethod
    public void setUp() {
        ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
        faker = new Faker(new Locale(config.locale()));
        RestAssured.baseURI = config.baseUrl();

        System.out.println("==================================================");
        System.out.println(config.baseUrl());
    }

    @Test
    public void testCanRegisterNewUser() {
        // given
        UserPayload user = new UserPayload()
                .username(faker.name().username())
                .email("test@test.com")
                .password("test123");

        // expect
        UserRegistrationResponse response = userApiService.registerUser(user)
                .shouldHave(Conditions.statusCode(HttpStatus.SC_OK))
                .asPojo(UserRegistrationResponse.class);

        response.getId();

//                .shouldHave(Conditions.bodyField("id", not(isEmptyOrNullString())));
    }

    @Test
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
