package programmer_zaman_now.belajar_spring_restful_api.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
