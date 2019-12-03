import config.TestConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.get;

public class GPathJSONTests extends TestConfig {
    @Test
    public void extractMapOfElementWithFind() {
        Response response = get("/competitions/2013/teams");

        Map<String, ?> allTeamDataForSingleTeam = response.path("teams.find { it.name == 'Cruzeiro EC' }");

        System.out.println(allTeamDataForSingleTeam);
    }
}
