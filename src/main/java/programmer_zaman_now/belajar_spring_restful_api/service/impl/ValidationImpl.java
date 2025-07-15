package programmer_zaman_now.belajar_spring_restful_api.service.impl;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import programmer_zaman_now.belajar_spring_restful_api.model.request.RegisterUserRequest;
import programmer_zaman_now.belajar_spring_restful_api.service.ValidationService;

import java.util.Set;

@Service
public class ValidationImpl implements ValidationService {

    @Autowired
    private Validator validator;

    @Override
    public void validate(Object request) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()){
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
