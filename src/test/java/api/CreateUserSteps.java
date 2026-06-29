package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.CreateUsers;
import models.LoginUsers;
import static io.restassured.RestAssured.given;

public class CreateUserSteps {

    @Step("Отправка POST-запроса на регистрацию пользователя")
    public Response register(CreateUsers user) {
        return given()
                .filter(new io.qameta.allure.restassured.AllureRestAssured())
                .header("Content-Type", "application/json")
                .baseUri(Endpoints.BASE_URL)
                .body(user)
                .when()
                .post(Endpoints.USER_REGISTER);
    }


    @Step("Отправка POST-запроса на авторизацию пользователя (логин)")
    public Response login(LoginUsers loginUsers) {
        return given()
                .filter(new io.qameta.allure.restassured.AllureRestAssured())
                .header("Content-Type", "application/json")
                .baseUri(Endpoints.BASE_URL)
                .body(loginUsers)
                .when()
                .post(Endpoints.USER_LOGIN);
    }
}