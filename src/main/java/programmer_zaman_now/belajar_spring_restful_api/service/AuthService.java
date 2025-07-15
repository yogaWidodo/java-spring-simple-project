package programmer_zaman_now.belajar_spring_restful_api.service;

import jakarta.transaction.Transactional;
import programmer_zaman_now.belajar_spring_restful_api.model.request.LoginUserRequest;
import programmer_zaman_now.belajar_spring_restful_api.model.response.TokenResponse;

public interface AuthService {
    @Transactional
    TokenResponse login(LoginUserRequest request);


}
