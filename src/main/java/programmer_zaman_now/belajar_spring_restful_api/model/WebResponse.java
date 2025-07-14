package programmer_zaman_now.belajar_spring_restful_api.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebResponse <T>{
    private T Data;
    private String errors;
}
