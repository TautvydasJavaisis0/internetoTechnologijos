package springframework.randomApp.services.forms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CatForm {
    String id;
    @JsonIgnore String user_id;
    @JsonIgnore String image_id;
    String sub_id;
    @JsonIgnore String created_at;
    ImageForm image;
}
