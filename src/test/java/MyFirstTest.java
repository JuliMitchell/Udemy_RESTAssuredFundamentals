

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
}
