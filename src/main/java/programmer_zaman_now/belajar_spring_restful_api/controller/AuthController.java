package programmer_zaman_now.belajar_spring_restful_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import programmer_zaman_now.belajar_spring_restful_api.entity.User;
import programmer_zaman_now.belajar_spring_restful_api.model.WebResponse;
import programmer_zaman_now.belajar_spring_restful_api.model.request.LoginUserRequest;
import programmer_zaman_now.belajar_spring_restful_api.model.response.TokenResponse;
import programmer_zaman_now.belajar_spring_restful_api.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(
            path = "/login",
            produces = "application/json",
            consumes = "application/json"
    )
    public WebResponse<TokenResponse> login(@RequestBody LoginUserRequest request) {
        TokenResponse tokenResponse = authService.login(request);
        return WebResponse.<TokenResponse>builder()
                .Data(tokenResponse)
                .build();
    }

    @DeleteMapping(
            path = "/logout",
            produces = "application/json"
    )
    public WebResponse<String> logout(User user) {
        authService.logout(user);
        return WebResponse.<String>builder()
                .Data("OK")
                .build();
    }

}
