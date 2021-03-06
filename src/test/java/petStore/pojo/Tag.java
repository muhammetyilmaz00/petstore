package petStore.pojo;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Tag {

    @SerializedName("id")
    public int id;
    @SerializedName("name")
    public String name;
}
