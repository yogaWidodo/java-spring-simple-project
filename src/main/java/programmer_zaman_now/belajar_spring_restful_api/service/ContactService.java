package programmer_zaman_now.belajar_spring_restful_api.service;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import programmer_zaman_now.belajar_spring_restful_api.entity.User;
import programmer_zaman_now.belajar_spring_restful_api.model.request.CreateContactRequest;
import programmer_zaman_now.belajar_spring_restful_api.model.request.SearchContactRequest;
import programmer_zaman_now.belajar_spring_restful_api.model.request.UpdateContactRequest;
import programmer_zaman_now.belajar_spring_restful_api.model.response.ContactResponse;

public interface ContactService {
    @Transactional
    ContactResponse create(User user, CreateContactRequest request);

    @Transactional
    ContactResponse update(User user, UpdateContactRequest request);

    @Transactional
    void delete(User user, String id);

    @Transactional
    ContactResponse get(User user, String id);

    @Transactional(readOnly = true)
    Page<ContactResponse> search(
            User user,
            SearchContactRequest request
    );

}
