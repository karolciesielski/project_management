package pl.management.project.project_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.management.project.project_management.model.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // SELECT email FROM user
    User findByEmail(String email);

    // SELECT login FROM user
    User findByLogin(String login);
}