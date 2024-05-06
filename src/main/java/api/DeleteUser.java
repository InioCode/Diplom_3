package api;

import io.restassured.RestAssured;

import static api.UrlConstants.DELETE_URL;

public class DeleteUser {


    public static void deleteUser(String accessToken){
        RestAssured.given()
                .auth()
                .oauth2(accessToken)
                .delete(DELETE_URL);
    }

}
