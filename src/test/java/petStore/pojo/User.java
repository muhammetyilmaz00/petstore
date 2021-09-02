package petStore.pojo;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {

    private int httpStatusCode;
    private String responseMessage;

    @SerializedName("id")
    public int id;
    @SerializedName("username")
    public String username;
    @SerializedName("firstName")
    public String firstName;
    @SerializedName("lastName")
    public String lastName;
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;
    @SerializedName("phone")
    public String phone;
    @SerializedName("userStatus")
    public int userStatus;
    @SerializedName("code")
    public int code;
    @SerializedName("type")
    public String type;
    @SerializedName("message")
    public String message;
}
