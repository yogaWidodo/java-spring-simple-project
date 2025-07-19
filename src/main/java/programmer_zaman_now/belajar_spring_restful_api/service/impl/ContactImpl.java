package programmer_zaman_now.belajar_spring_restful_api.service.impl;


import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import programmer_zaman_now.belajar_spring_restful_api.entity.Contact;
import programmer_zaman_now.belajar_spring_restful_api.entity.User;
import programmer_zaman_now.belajar_spring_restful_api.model.request.CreateContactRequest;
import programmer_zaman_now.belajar_spring_restful_api.model.request.SearchContactRequest;
import programmer_zaman_now.belajar_spring_restful_api.model.request.UpdateContactRequest;
import programmer_zaman_now.belajar_spring_restful_api.model.response.ContactResponse;
import programmer_zaman_now.belajar_spring_restful_api.repository.ContactRepository;
import programmer_zaman_now.belajar_spring_restful_api.service.ContactService;
import programmer_zaman_now.belajar_spring_restful_api.service.ValidationService;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;




@Service
public class ContactImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ValidationService validationService;

    @Override
    public ContactResponse create(User user, CreateContactRequest request) {
        validationService.validate(request);
        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setUser(user);
        contactRepository.save(contact);

        return toResponse(contact);
    }



    @Override
    public ContactResponse update(User user, UpdateContactRequest request) {
        validationService.validate(request);
        Contact contact = contactRepository.findFirstByUserAndId(user,request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contactRepository.save(contact);
        return toResponse(contact);
    }

    @Override
    public void delete(User user, String id) {
        Contact contact = contactRepository.findFirstByUserAndId(user, id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));
        contactRepository.delete(contact);
        toResponse(contact);
    }

    @Override
    public ContactResponse get(User user, String id) {
        Contact contact = contactRepository.findFirstByUserAndId(user, id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));
        if (contact != null) {
            return toResponse(contact);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found");
        }
    }


    @Override
    public Page<ContactResponse> search(User user, SearchContactRequest request) {
        Specification<Contact> specification = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(builder.equal(root.get("user"), user));
            if (Objects.nonNull(request.getName())) {
                predicates.add(builder.or(
                        builder.like(root.get("firstName"), "%" + request.getName() + "%"),
                        builder.like(root.get("lastName"), "%" + request.getName() + "%")
                ));
            }
            if (Objects.nonNull(request.getEmail())) {
                predicates.add(builder.like(root.get("email"), "%" + request.getEmail() + "%"));
            }
            if (Objects.nonNull(request.getPhone())) {
                predicates.add(builder.like(root.get("phone"), "%" + request.getPhone() + "%"));
            }

            assert query != null;
            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Contact> contacts = contactRepository.findAll(specification, pageable);
        List<ContactResponse> contactResponses = contacts.getContent().stream()
                .map(this::toResponse)
                .toList();

        return new PageImpl<>(contactResponses, pageable, contacts.getTotalElements());
    }


    private ContactResponse toResponse(Contact contact) {
        return ContactResponse.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .phone(contact.getPhone())
                .build();
    }
}
