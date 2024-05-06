package Api.JsonObjects;

import java.util.List;

public class SuccessRegisterUserData {
    private String success;
    private List<UserData> UserData;
    private String accessToken;
    private String refreshToken;

    public SuccessRegisterUserData(String success, List<UserData> userData, String accessToken, String refreshToken) {
        this.success = success;
        UserData = userData;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public SuccessRegisterUserData() {
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<UserData> getUserData() {
        return UserData;
    }

    public void setUserData(List<UserData> userData) {
        UserData = userData;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
