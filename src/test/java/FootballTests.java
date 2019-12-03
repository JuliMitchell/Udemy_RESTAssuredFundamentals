import config.TestConfig;
import org.junit.Test;
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
}
