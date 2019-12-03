import config.EndPoint;
import config.TestConfig;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsdInClasspath;

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

    @Test
    public void updateGame(){
        String gameBody = "{\n" +
                "    \"id\": 2,\n" +
                "    \"name\": \"Gran Turismo 3\",\n" +
                "    \"releaseDate\": \"2001-03-10\",\n" +
                "    \"reviewScore\": 90,\n" +
                "    \"category\": \"Driving\",\n" +
                "    \"rating\": \"Universal\"\n" +
                "  }";

        given()
                .body(gameBody)
                .log().all()
        .when().put(EndPoint.VIDEOGAMES + "/2")
        .then()
                .log().all();
    }

    @Test
    public void deleteGame(){
        given().when().delete(EndPoint.VIDEOGAMES+ "/11").then().log().all();
    }

    @Test
    public void getSingleGame(){
        given()
                .pathParam("videoGameId", 5)
        .when()
                .get( EndPoint.VIDEOGAMES + "/{videoGameId}")
        .then()
                .log().all();
    }

    @Test
    public void testSerialisationJSON(){
        VideoGame videoGame = new VideoGame("87", "2019-01-04", "GTA V", "10", "21", "Mature");

        given()
            .body(videoGame)
        .when().post(EndPoint.VIDEOGAMES)
        .then()
            .log().all();
    }

    @Test
    public void testVideoGameSchemaXML(){
        given()
            .pathParam("videoGameId", 6)
        .when()
            .get(EndPoint.VIDEOGAMES + "/{videoGameId}")
        .then()
            .body(matchesXsdInClasspath("VideoGame.xsd"));
    }
}
