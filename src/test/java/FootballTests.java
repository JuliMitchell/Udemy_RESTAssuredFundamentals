import config.TestConfig;
import io.restassured.RestAssured.*;
import org.junit.Test;

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
}
