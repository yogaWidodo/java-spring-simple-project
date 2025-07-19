package programmer_zaman_now.belajar_spring_restful_api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import programmer_zaman_now.belajar_spring_restful_api.entity.User;
import programmer_zaman_now.belajar_spring_restful_api.model.WebResponse;
import programmer_zaman_now.belajar_spring_restful_api.model.request.CreateContactRequest;
import programmer_zaman_now.belajar_spring_restful_api.model.request.SearchContactRequest;
import programmer_zaman_now.belajar_spring_restful_api.model.request.UpdateContactRequest;
import programmer_zaman_now.belajar_spring_restful_api.model.response.ContactResponse;
import programmer_zaman_now.belajar_spring_restful_api.model.response.PagingResponse;
import programmer_zaman_now.belajar_spring_restful_api.service.ContactService;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping(
            produces = "application/json",
            consumes = "application/json"
    )
    WebResponse<ContactResponse> createContact(User user, @RequestBody CreateContactRequest request) {
        ContactResponse contactResponse = contactService.create(user, request);
        return WebResponse.<ContactResponse>builder()
                .Data(contactResponse)
                .build();
    }

    @PutMapping(
            path = "/{id}",
            produces = "application/json",
            consumes = "application/json"
    )
    WebResponse<ContactResponse> updateContact(
            User user,
            @RequestBody UpdateContactRequest request,
            @PathVariable("id") String id) {
        request.setId(id);
        ContactResponse contactResponse = contactService.update(user, request);
        return WebResponse.<ContactResponse>builder()
                .Data(contactResponse)
                .build();
    }

    @DeleteMapping(
            path = "/{id}",
            produces = "application/json"
    )
    WebResponse<String> deleteContact(User user, @PathVariable("id") String id) {
        contactService.delete(user, id);
        return WebResponse.<String>builder()
                .Data("OK")
                .build();
    }

    @GetMapping(
            path = "/{id}",
            produces = "application/json"
    )
    WebResponse<ContactResponse> getContact(User user, @PathVariable String id) {
        ContactResponse contactResponse = contactService.get(user, id);
        return WebResponse.<ContactResponse>builder()
                .Data(contactResponse)
                .build();
    }

    @GetMapping(
            produces = "application/json"
    )
    public WebResponse<List<ContactResponse>> search
            (User user,
             @RequestParam(value = "name", required = false) String name,
             @RequestParam(value = "email", required = false) String email,
             @RequestParam(value = "phone", required = false) String phone,
             @RequestParam(value = "page", defaultValue = "0") int page,
             @RequestParam(value = "size", defaultValue = "10") int size
            ) {
        SearchContactRequest request = SearchContactRequest.builder()
                .name(name)
                .email(email)
                .phone(phone)
                .page(page)
                .size(size)
                .build();
        contactService.search(user, request);
        Page<ContactResponse> contactResponses = contactService.search(user,request);
        return WebResponse.<List<ContactResponse>>builder()
                .Data(contactResponses.getContent())
                .pagingResponse(
                        PagingResponse.builder()
                                .currentPage(contactResponses.getNumber())
                                .totalPage(contactResponses.getTotalPages())
                                .size(contactResponses.getSize())
                                .build())
                .build();
    }
}
