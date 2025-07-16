package programmer_zaman_now.belajar_spring_restful_api.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import programmer_zaman_now.belajar_spring_restful_api.entity.User;
import programmer_zaman_now.belajar_spring_restful_api.model.request.RegisterUserRequest;
import programmer_zaman_now.belajar_spring_restful_api.model.request.UpdateUserRequest;
import programmer_zaman_now.belajar_spring_restful_api.model.response.UserResponse;
import programmer_zaman_now.belajar_spring_restful_api.repository.UserRepository;
import programmer_zaman_now.belajar_spring_restful_api.security.BCrypt;
import programmer_zaman_now.belajar_spring_restful_api.service.UserService;
import programmer_zaman_now.belajar_spring_restful_api.service.ValidationService;


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

    @Override
    public UserResponse update(User user, UpdateUserRequest request) {
        validationService.validate(request);
        if (request.getName()!= null) {
            user.setName(request.getName());
        }
        if (request.getPassword() != null) {
            user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        }
        userRepository.save(user);
        return UserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }

    @Override
    public UserResponse getUser(User user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }


}
