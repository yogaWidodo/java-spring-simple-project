package programmer_zaman_now.belajar_spring_restful_api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import programmer_zaman_now.belajar_spring_restful_api.model.WebResponse;
import programmer_zaman_now.belajar_spring_restful_api.model.request.RegisterUserRequest;
import programmer_zaman_now.belajar_spring_restful_api.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    // Endpoint untuk register user
    @PostMapping(
            produces = "application/json",
            consumes = "application/json"
    )
    public WebResponse<String> registerUser(@RequestBody  RegisterUserRequest request) {
        userService.register(request);
        return WebResponse.<String>builder()
                .Data("OK")
                .build();
    }
}
