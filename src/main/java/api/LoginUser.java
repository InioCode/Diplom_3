package api;

import api.json.LoginUserBodyData;
import api.json.SuccessLoginUserData;
import io.qameta.allure.Step;
import io.restassured.RestAssured;

import static api.UrlConstants.LOGIN_URL;

public class LoginUser {
    @Step("Получение Access Token через запрос API")
    public static String loginUserAndGetToken(String email, String password){
        LoginUserBodyData login = new LoginUserBodyData(email, password);

        return RestAssured.given()
                .header("Content-type", "application/json")
                .body(login)
                .and()
                .post(LOGIN_URL)
                .as(SuccessLoginUserData.class)
                .getAccessToken().substring(7);
    }

}
