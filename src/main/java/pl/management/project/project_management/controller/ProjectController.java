package pl.management.project.project_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.management.project.project_management.service.ProjectService;

@Controller
public class ProjectController {

    ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/project/add/{acronim}")
    public String addProject(String acronim){
        projectService.addProject(acronim);
        return "";
    }

    @GetMapping("/project/task/add/{acronim}")
    public String addTask(String acronim){
        projectService.addTaskToProject(acronim);
        return "";
    }
}
