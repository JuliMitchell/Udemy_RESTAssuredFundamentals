import config.EndPoint;
import config.TestConfig;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class VideoGameDBTests extends TestConfig {

    @Test
    public void getAllGames(){
        given()
        .when().get(EndPoint.VIDEOGAMES)
        .then();
    }

    @Test
    public void createNewGameByJSON(){
        String gameBodyJson = "{\n" +
                "  \"id\": 16,\n" +
                "  \"name\": \"Mario Bros\",\n" +
                "  \"releaseDate\": \"2019-12-02T18:13:58.789Z\",\n" +
                "  \"reviewScore\": 50,\n" +
                "  \"category\": \"Arcade\",\n" +
                "  \"rating\": \"Everyone\"\n" +
                "}";
        given()
                .body(gameBodyJson)
                .log().all()
        .when().post(EndPoint.VIDEOGAMES)
        .then()
                .log().all();
    }

}
