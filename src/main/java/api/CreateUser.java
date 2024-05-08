package api;

import api.json.CreateUserBodyData;
import api.json.SuccessRegisterUserData;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static api.UrlConstants.REGISTRATION_URL;

public class CreateUser {
    public static boolean userCreated = false;
    @Step("Создание пользователя через запрос API")
    public static String createUserAndGetToken(String email, String password){

        CreateUserBodyData bodyCreateUser= new CreateUserBodyData(email, password, "Ivan");
        Response response = RestAssured
                .given()
                .header("Content-type", "application/json" )
                .body(bodyCreateUser)
                .and()
                .post(REGISTRATION_URL);

        if (response.statusCode() == 200){
              userCreated = true;
        }

        return response
                .as(SuccessRegisterUserData.class)
                .getAccessToken();
    }
}
