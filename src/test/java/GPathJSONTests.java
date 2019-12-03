import config.TestConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;

public class GPathJSONTests extends TestConfig {

    @Test
    public void extractMapOfElementWithFind() {
        Response response = get("/competitions/2013/teams");
        Map<String, ?> allTeamDataForSingleTeam = response.path("teams.find { it.name == 'Cruzeiro EC' }");
        System.out.println(allTeamDataForSingleTeam);
    }

    @Test
    public void extractSingleValueWithFind(){
        Response response = get("/teams/57");
        String certainPlayer = response.path("squad.find {it.shirtNumber == 16}.name");
        System.out.println("Player with shirt number 16 is " + certainPlayer);
    }

    @Test
    public void extractMultipleValuesWithFind(){
        Response response = get("/teams/57");
        List<String> squadPlayersName = response.path("squad.findAll {it.role == 'PLAYER'}.name");
        System.out.println("Players in the squad: ");
        squadPlayersName.stream().forEach(playerName -> System.out.println(playerName));
    }
}
