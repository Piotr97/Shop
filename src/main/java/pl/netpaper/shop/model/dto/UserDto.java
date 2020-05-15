package pl.netpaper.shop.model.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) //jezeli jakas wartosc w objekcie bd null to nie doda do json

public class UserDto {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
}
