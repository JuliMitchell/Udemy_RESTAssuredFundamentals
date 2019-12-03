import config.TestConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

import static io.restassured.RestAssured.*;

public class FootballTests extends TestConfig {

    @Test
    public void getAllCompetitionsOneSeason(){
        given()
            .spec(football_requestSpecification)
            .queryParam("plan", "TIER_ONE")
            .log().all()
        .when()
            .get("/competitions")
        .then()
            .log().all();
    }

    @Test
    public void getTeamCount(){
        given()
            .spec(football_requestSpecification)
        .when().get("/competitions/2013/teams")
        .then()
            .body("count", equalTo(20));
    }

    @Test
    public void getFirstTeamName(){
        given()
            .spec(football_requestSpecification)
        .when().get("/competitions/2013/teams")
        .then()
            .body("teams.name[0]", equalTo("Fluminense FC"));
    }

    @Test
    public void getAllTeamData(){
        String responseBody =
                given()
                    .spec(football_requestSpecification)
                .when().get("/competitions/2013/teams")
                    .asString();
        System.out.println(responseBody);
    }

    @Test
    public void getAllTeamDataThenConvert(){
        Response response =
                given()
                    .spec(football_requestSpecification)
                .when()
                    .get("/competitions/2013/teams")
                .then()
                    .contentType(ContentType.JSON)
                    .extract().response();

        String jsonResponseAsString = response.asString();

        System.out.println(jsonResponseAsString);

    }

    @Test
    public void getHeaders(){
        Response response =
                given()
                    .spec(football_requestSpecification)
                .when().get("/competitions/2013/teams")
                .then()
                    .contentType(ContentType.JSON)
                    .extract().response();

        Headers headers = response.getHeaders();

        System.out.println("Headers: ");
        headers.asList().stream().forEach(header -> System.out.println(header));

        String client = response.getHeader("X-Authenticated-Client");
        System.out.println("Client: " + client);
    }

    @Test
    public void getFirstTeamNameJsonPath(){
        String fistTeamName =
                given()
                    .spec(football_requestSpecification)
                .when().get("/competitions/2013/teams")
                    .jsonPath().getString("teams.name[0]");
        System.out.println(fistTeamName);
    }

    @Test
    public void getAllTeamNames(){
        Response response =
                given()
                    .spec(football_requestSpecification)
                .when().get("/competitions/2013/teams")
                .then()
                    .contentType(ContentType.JSON)
                    .extract().response();

        List<String> teamNames = response.path("teams.name");

        teamNames.stream().forEach(name -> System.out.println(name));

    }
}
