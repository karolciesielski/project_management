package pl.management.project.project_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.management.project.project_management.model.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findOneRoleById(Integer id);
}
