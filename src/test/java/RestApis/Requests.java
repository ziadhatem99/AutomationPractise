package RestApis;

import Base.Assets;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Requests {
    Assets assets = new Assets();

    public Response authenticateUser(String userName, String password) {
        return given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("username", userName)
                .formParam("password", password)
                .when().log().all()
                .post(assets.AuthenticateEndpointUrl);
    }

    public String getSessionId(Response response) {
        return response.jsonPath().getString("session_id");
    }


    public Response fetchCars(String sessionId) {
        return given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Session-Id", sessionId)
                .when().log().all()
                .get(assets.FetchCarsEndpointUrl);
    }
}
