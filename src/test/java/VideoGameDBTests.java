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

    @Test
    public void createNewGameByXML(){
        String gameBodyXML = "<videoGame category=\"Driving\" rating=\"Universal\">\n" +
                "    <id>19</id>\n" +
                "    <name>Gran Turismo 5</name>\n" +
                "    <releaseDate>2001-03-10T00:00:00-03:00</releaseDate>\n" +
                "    <reviewScore>93</reviewScore>\n" +
                "  </videoGame>";

        given()
            .body(gameBodyXML)
            .log().all()
        .when().post(EndPoint.VIDEOGAMES)
        .then()
            .log().all();
    }

}
