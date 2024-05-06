package Api;

import Api.JsonObjects.CreateUserBodyData;
import Api.JsonObjects.SuccessRegisterUserData;
import io.restassured.RestAssured;

import static Api.UrlConstants.REGISTRATION_URL;

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
