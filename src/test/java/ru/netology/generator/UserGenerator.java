package ru.netology.generator;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import ru.netology.domain.Registration;

import java.util.Locale;

public class UserGenerator {

    private static RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static void makeRequest(Registration registration) {
        RestAssured.given()
                .spec(requestSpecification)
                .body(registration)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static Registration getValidActiveUser() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName();
        String password = faker.internet().password();
        String status = "active";
        Registration registration = new Registration(login, password, status);
        makeRequest(registration);
        return registration;
    }

    public static Registration getValidBlockedUser() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName();
        String password = faker.internet().password();
        String status = "blocked";
        Registration registration = new Registration(login, password, status);
        makeRequest(registration);
        return registration;
    }

    public static Registration getUserWithIncorrectPassword() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().firstName();
        String password = "password";
        String status = "active";
        Registration registration = new Registration(login, password, status);
        makeRequest(registration);
        return new Registration(login, "incorrectpassword", status);
    }

    public static Registration getUserWithIncorrectLogin() {
        Faker faker = new Faker(new Locale("en"));
        String login = "vasya";
        String password = faker.internet().password();
        String status = "active";
        Registration registration = new Registration(login, password, status);
        makeRequest(registration);
        return new Registration("petya", password, status);
    }
}