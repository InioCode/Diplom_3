package Api;

import Api.JsonObjects.LoginUserBodyData;
import Api.JsonObjects.SuccessLoginUserData;
import io.restassured.RestAssured;

import static Api.UrlConstants.LOGIN_URL;

public class LoginUser {

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
