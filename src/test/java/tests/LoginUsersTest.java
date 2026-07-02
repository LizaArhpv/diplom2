package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import api.CreateUserSteps;
import api.RandomUsers;
import models.CreateUsers;
import models.LoginUsers;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@DisplayName("Авторизация пользователя в Stellar Burgers")
public class LoginUsersTest extends BaseTest {

    private CreateUserSteps userSteps;
    private CreateUsers registeredUser;

    @Before
    public void localSetUp() {
        userSteps = new CreateUserSteps();
        registeredUser = RandomUsers.generate();
        Response response = userSteps.register(registeredUser);
        accessToken = response.path("accessToken");
    }

    @Test
    @DisplayName("Успешная авторизация существующего пользователя")
    @Description("Код 200, success true и токены в ответе на запрос авторизации")
    public void successUserAuthorisation() {
        LoginUsers loginUsers = new LoginUsers(registeredUser.getEmail(), registeredUser.getPassword());
        Response response = userSteps.login(loginUsers);
        response.then()
                .statusCode(SC_OK)
                .body("success", equalTo(true))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());
    }

    @Test
    @DisplayName("Ошибка авторизации с неверным паролем")
    @Description("Код 401 и success false при попытке входа с некорректным паролем")
    public void cannotAuthoriseWithWrongPassword() {
        LoginUsers loginUsersWithWrongPass = new LoginUsers(registeredUser.getEmail(), "password_null");
        Response response = userSteps.login(loginUsersWithWrongPass);
        response.then()
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }
    @Test
    @DisplayName("Ошибка авторизации с неверным логином")
    @Description("Код 401 и success false при попытке входа с некорректным логином")
    public void cannotAuthoriseWithWrongEmail() {
        LoginUsers loginUsersWithWrongEmail = new LoginUsers("email_null", registeredUser.getPassword());
        Response response = userSteps.login(loginUsersWithWrongEmail);
        response.then()
                .statusCode(SC_UNAUTHORIZED)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }
    }
