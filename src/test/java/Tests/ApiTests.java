package Tests;

import Base.Assets;
import RestApis.Requests;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiTests {
    Requests requests = new Requests();
    Assets assets = new Assets();

    @Test(description = "Validate the cars fetched successfully")
    public void happyScenario() {
        Response response = requests.authenticateUser(assets.user1Username, assets.user1Password);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(), 200);
        String sessionId = requests.getSessionId(response);

        //Fetch Cars
        Response carResponse = requests.fetchCars(sessionId);
        carResponse.then().log().body();
        Assert.assertEquals(carResponse.getStatusCode(), 200);
        System.out.println("Cars fetched: " + carResponse.asString());
    }

    @Test(description = "Test With invalid UserName")
    public void invalidUserScenario() {
        Response response = requests.authenticateUser("invalidUser", "password123");
        response.then().log().body();
//        Assert.assertEquals(response.getStatusCode() , 401);
        response.then().body("message", Matchers.equalTo("Invalid username or password"));
    }

    @Test(description = "Test With Invalid Session Id")
    public void invalidSessionIdScenario() {
        Response carResponse = requests.fetchCars("445643545646");
        carResponse.then().log().body();
//        Assert.assertEquals(carResponse.getStatusCode() , 401);
        carResponse.then().body("message", Matchers.equalTo("Invalid session ID"));
    }
}
