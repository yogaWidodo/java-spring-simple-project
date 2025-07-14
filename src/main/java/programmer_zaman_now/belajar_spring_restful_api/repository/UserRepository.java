package programmer_zaman_now.belajar_spring_restful_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import programmer_zaman_now.belajar_spring_restful_api.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
