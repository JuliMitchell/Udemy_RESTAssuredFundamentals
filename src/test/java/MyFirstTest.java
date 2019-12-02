

import org.junit.Test;

import static io.restassured.RestAssured.*;

public class MyFirstTest {
    @Test
    public void myFistTest(){
        given()
        .when().get("http://localhost:8080/app/videogames/2")
        .then().statusCode(200);
    }
}
