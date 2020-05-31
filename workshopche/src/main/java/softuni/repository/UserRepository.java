package softuni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.model.entity.User;
import softuni.model.service.UserServiceModel;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

}
