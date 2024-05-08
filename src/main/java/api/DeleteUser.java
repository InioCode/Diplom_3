package api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;

import static api.UrlConstants.DELETE_URL;

public class DeleteUser {

    @Step("Удаление пользователя через запрос API")
    public static void deleteUser(String accessToken){
        RestAssured.given()
                .auth()
                .oauth2(accessToken)
                .delete(DELETE_URL);
    }

}
