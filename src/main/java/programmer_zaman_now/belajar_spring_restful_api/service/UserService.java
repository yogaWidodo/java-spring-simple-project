package programmer_zaman_now.belajar_spring_restful_api.service;

import org.springframework.transaction.annotation.Transactional;
import programmer_zaman_now.belajar_spring_restful_api.entity.User;
import programmer_zaman_now.belajar_spring_restful_api.model.request.RegisterUserRequest;
import programmer_zaman_now.belajar_spring_restful_api.model.request.UpdateUserRequest;
import programmer_zaman_now.belajar_spring_restful_api.model.response.UserResponse;


public interface UserService {

    @Transactional
    void register(RegisterUserRequest request);

    @Transactional
    UserResponse update(User user, UpdateUserRequest request);

    UserResponse getUser(User user);
}
