package programmer_zaman_now.belajar_spring_restful_api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import programmer_zaman_now.belajar_spring_restful_api.entity.User;
import programmer_zaman_now.belajar_spring_restful_api.model.WebResponse;
import programmer_zaman_now.belajar_spring_restful_api.model.request.RegisterUserRequest;
import programmer_zaman_now.belajar_spring_restful_api.model.request.UpdateUserRequest;
import programmer_zaman_now.belajar_spring_restful_api.model.response.UserResponse;
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
    public WebResponse<String> registerUser(@RequestBody RegisterUserRequest request) {
        userService.register(request);
        return WebResponse.<String>builder()
                .Data("OK")
                .build();
    }

    @GetMapping(
            path = "/current",
            produces = "application/json"
    )
    public WebResponse<UserResponse> getUser(User user) {
        UserResponse userResponse = userService.getUser(user);
        return WebResponse.<UserResponse>builder()
                .Data(userResponse)
                .build();
    }


    @PatchMapping(
            path = "/current",
            produces = "application/json",
            consumes = "application/json"
    )
    public WebResponse<UserResponse> updateUser(
            User user,
            @RequestBody UpdateUserRequest request) {
        UserResponse userResponse = userService.update(user, request);
        return WebResponse.<UserResponse>builder()
                .Data(userResponse)
                .build();
    }
}
