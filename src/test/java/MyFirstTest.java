

import config.TestConfig;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class MyFirstTest extends TestConfig {
    //My first test
    @Test
    public void myFistTest(){
        given()
        .when().get("/videogames/2")
        .then().statusCode(200);
    }

    @Test
    public void mySecondTest(){
        given()
                .log()
                .all()
                .when().get("/videogames/2")
                .then().statusCode(200);
    }

    @Test
    public void myThirdTest(){
        given()
                .log()
                .ifValidationFails()
                .when().get("/videogames/2")
                .then().statusCode(201);
    }

    @Test
    public void myFourthTest(){
        given()
                .spec(videoGame_requestSpecification)
                .log()
                .all()
                .when().get("/videogames/2")
                .then()
                .spec(responseSpecification)
                .log()
                .all();
    }
}
