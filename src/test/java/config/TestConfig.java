package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

public class TestConfig {

    public static RequestSpecification videoGame_requestSpecification;
    public static RequestSpecification football_requestSpecification;

    public static ResponseSpecification responseSpecification;

    @BeforeClass
    public static void setUp(){

        //RestAssured.proxy("localhost", 8888);

        videoGame_requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(8080)
                .setBasePath("/app")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        football_requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://api.football-data.org")
                .setBasePath("/v2")
                .addHeader("X-Auth-Token", "a64bb1b7c3ba479e8f7078f2891d9b36")
                .addHeader("X-Response-Control", "minified")
                .build();

        RestAssured.requestSpecification = videoGame_requestSpecification;

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();

        RestAssured.responseSpecification = responseSpecification;
    }
}
