package xcini.rv.com.xcini.Beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 30/12/17.
 */

public class ForgotPassword_Pojo {
    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("error")
    @Expose
    private String error;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
