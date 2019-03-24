package pl.management.project.project_management.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
