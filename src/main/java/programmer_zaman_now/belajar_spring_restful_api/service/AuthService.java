package programmer_zaman_now.belajar_spring_restful_api.service;

import org.springframework.transaction.annotation.Transactional;
import programmer_zaman_now.belajar_spring_restful_api.entity.User;
import programmer_zaman_now.belajar_spring_restful_api.model.request.LoginUserRequest;
import programmer_zaman_now.belajar_spring_restful_api.model.response.TokenResponse;

public interface AuthService {
    @Transactional
    TokenResponse login(LoginUserRequest request);

    @Transactional
    void logout(User user);


}
