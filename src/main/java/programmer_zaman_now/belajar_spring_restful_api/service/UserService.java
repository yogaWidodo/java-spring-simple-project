package programmer_zaman_now.belajar_spring_restful_api.service;

import jakarta.transaction.Transactional;
import programmer_zaman_now.belajar_spring_restful_api.model.request.RegisterUserRequest;


public interface UserService {

    @Transactional
    void register(RegisterUserRequest request);
}
