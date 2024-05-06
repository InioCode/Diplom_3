package api;

import api.json.CreateUserBodyData;
import api.json.SuccessRegisterUserData;
import io.restassured.RestAssured;

import static api.UrlConstants.REGISTRATION_URL;

public class CreateUser {
    public static String createUserAndGetToken(String email, String password){


        CreateUserBodyData bodyCreateUser= new CreateUserBodyData(email, password, "Ivan");

         return RestAssured
                .given()
                .header("Content-type", "application/json" )
                .body(bodyCreateUser)
                .and()
                .post(REGISTRATION_URL)
                 .as(SuccessRegisterUserData.class)
                 .getAccessToken();
    }
}
