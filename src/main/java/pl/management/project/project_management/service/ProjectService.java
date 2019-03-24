package pl.management.project.project_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.management.project.project_management.model.entity.Project;
import pl.management.project.project_management.model.entity.Task;
import pl.management.project.project_management.repository.ProjectRepository;
import pl.management.project.project_management.repository.TaskRepository;

@Service
public class ProjectService {

    ProjectRepository projectRepository;
    TaskRepository taskRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
    }

    public void addProject(String acronim){
        projectRepository.save(new Project(acronim));
    }

    public void addUserToProject(){

    }

    public void addTaskToProject(String acronim){
        Project project = projectRepository.findByAcronim(acronim);
        Task task = new Task("task", project);
        project.addTask(task);
        taskRepository.save(task);
    }
}
