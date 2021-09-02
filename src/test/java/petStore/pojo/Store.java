package petStore.pojo;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Store {

    private int httpStatusCode;
    private String responseMessage;

    @SerializedName("id")
    public int id;
    @SerializedName("petId")
    public int petId;
    @SerializedName("quantity")
    public int quantity;
    @SerializedName("shipDate")
    public String shipDate;
    @SerializedName("status")
    public String status;
    @SerializedName("complete")
    public boolean complete;
    @SerializedName("code")
    public int code;
    @SerializedName("type")
    public String type;
    @SerializedName("message")
    public String message;

}
