import config.TestConfig;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Test
    public void extractSingleValueWithHighestId(){
        Response response = get("/teams/57");
        String playerNameMaxId = response.path("squad.max {it.id}.name");
        System.out.println("The player with the highest id number is " + playerNameMaxId);
    }

    @Test
    public void extractSingleValueWithLowestId(){
        Response response = get("/teams/57");
        String playerNameMinId = response.path("squad.min {it.id}.name");
        System.out.println("The player with the lowest id number is " + playerNameMinId);
    }

    @Test
    public void extractMultipleValuesAndSumThem(){
        Response response = get("/teams/57");
        int sumOfShirtNumbers = response.path("squad.collect {it.id}.sum()");
        System.out.println("Sum: " + sumOfShirtNumbers);
    }

    @Test
    public void extractSingleValueWithFindAndFindAll(){
        Response response = get("/teams/57");
        String playersWithNumber = response.path("squad.findAll {it.shirtNumber != null}.min {it.shirtNumber}.name");
        System.out.println(playersWithNumber);
    }

    @Test
    public void extractMultipleValuesWithParameters(){

        Response response = get("/teams/57");

        String position = "Defender";
        String nationality = "Greece";
        Map<String,?> certainPlayer = response.path("squad.findAll {it.position == '%s'}.find {it.nationality == '%s'}", position, nationality);

        System.out.println(certainPlayer);
    }
}
