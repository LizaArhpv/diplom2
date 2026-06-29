package tests;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import api.CreateUserSteps;
import api.Endpoints;

public class BaseTest {

    protected String accessToken;
    protected CreateUserSteps userClient;

    @Before
    public void setUp() {
        RestAssured.baseURI = Endpoints.BASE_URL;
        userClient = new CreateUserSteps();
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }
}
