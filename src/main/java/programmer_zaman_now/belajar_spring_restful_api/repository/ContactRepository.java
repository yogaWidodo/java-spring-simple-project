package programmer_zaman_now.belajar_spring_restful_api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import programmer_zaman_now.belajar_spring_restful_api.entity.Contact;
import programmer_zaman_now.belajar_spring_restful_api.entity.User;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String>, JpaSpecificationExecutor<Contact> {

    Optional<Contact> findFirstByUserAndId(User user, String id);
}
