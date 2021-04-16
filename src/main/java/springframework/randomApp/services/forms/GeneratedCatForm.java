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
public class GeneratedCatForm {
    @JsonIgnore String[] breeds;
    String id;
    String url;
    int width;
    int height;
}
