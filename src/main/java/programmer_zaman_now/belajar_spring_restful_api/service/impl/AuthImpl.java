package programmer_zaman_now.belajar_spring_restful_api.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import programmer_zaman_now.belajar_spring_restful_api.entity.User;
import programmer_zaman_now.belajar_spring_restful_api.model.request.LoginUserRequest;
import programmer_zaman_now.belajar_spring_restful_api.model.response.TokenResponse;
import programmer_zaman_now.belajar_spring_restful_api.repository.UserRepository;
import programmer_zaman_now.belajar_spring_restful_api.security.BCrypt;
import programmer_zaman_now.belajar_spring_restful_api.service.AuthService;
import programmer_zaman_now.belajar_spring_restful_api.service.ValidationService;

import java.util.UUID;

@Service
public class AuthImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Override
    public TokenResponse login(LoginUserRequest request) {
        validationService.validate(request);
        User user = userRepository.findById(request.getUsername())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password"));

        if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(next30Days());
            userRepository.save(user);

            return TokenResponse.builder()
                    .token(user.getToken())
                    .expiredAt(user.getTokenExpiredAt())
                    .build();
        }else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }

    @Override
    public void logout(User user) {
        user.setToken(null);
        user.setTokenExpiredAt(null);
        userRepository.save(user);
    }

    private Long next30Days() {
        return System.currentTimeMillis() + (1000 * 16 * 24 * 30);
    }

}
