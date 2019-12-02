import config.EndPoint;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class VideoGameDBTests {

    @Test
    public void getAllGames(){
        given()
        .when().get(EndPoint.VIDEOGAMES)
        .then();
    }
}
