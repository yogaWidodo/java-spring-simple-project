package programmer_zaman_now.belajar_spring_restful_api.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import programmer_zaman_now.belajar_spring_restful_api.model.request.RegisterUserRequest;
import programmer_zaman_now.belajar_spring_restful_api.repository.UserRepository;


public interface UserService {

    @Transactional
    void register(RegisterUserRequest request);
}
