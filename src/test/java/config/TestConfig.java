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
                .addHeader("Content-Type", "application/xml")
                .addHeader("Accept", "application/xml")
                .build();

        football_requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://api.football-data.org/")
                .setBasePath("/v2")
                .addHeader("X-Response-Control", "minified")
                .build();


        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();

        RestAssured.responseSpecification = responseSpecification;
    }
}
