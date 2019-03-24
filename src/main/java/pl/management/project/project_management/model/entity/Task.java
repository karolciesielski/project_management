package pl.management.project.project_management.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name="task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "The name may not be blank")
    protected String name;

    private String description;
    private int sprint;
    private int weight;
    private int storyPoints;
    private int progress;
    private long user;
}
