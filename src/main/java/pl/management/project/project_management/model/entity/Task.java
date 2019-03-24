package pl.management.project.project_management.model.entity;

import lombok.Data;
import pl.management.project.project_management.model.enums.TaskProgress;
import pl.management.project.project_management.model.enums.TaskPriority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @Column(length = 5000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;

    private double position;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", length = 10)
    private TaskPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(name = "progress", length = 20, nullable = false)
    private TaskProgress progress;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private User assignedTo;

    public Task(@NotNull String name, Project project) {
        this.name = name;
        this.project = project;
    }

    public Task() {
    }
}
