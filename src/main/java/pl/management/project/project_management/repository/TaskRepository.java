package pl.management.project.project_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.management.project.project_management.model.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
