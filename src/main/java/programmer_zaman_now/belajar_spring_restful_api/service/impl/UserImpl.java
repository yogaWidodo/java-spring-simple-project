package programmer_zaman_now.belajar_spring_restful_api.service.impl;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import programmer_zaman_now.belajar_spring_restful_api.entity.User;
import programmer_zaman_now.belajar_spring_restful_api.model.request.RegisterUserRequest;
import programmer_zaman_now.belajar_spring_restful_api.repository.UserRepository;
import programmer_zaman_now.belajar_spring_restful_api.security.BCrypt;
import programmer_zaman_now.belajar_spring_restful_api.service.UserService;
import programmer_zaman_now.belajar_spring_restful_api.service.ValidationService;

import java.util.Set;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ValidationService validationService;

    @Override
    public void register(RegisterUserRequest request) {
        validationService.validate(request);
        if (userRepository.existsById(request.getUsername())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setName(request.getName());

        userRepository.save(user);


    }
}
