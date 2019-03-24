package pl.management.project.project_management.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String acronim;
    private String description;
    private String administrator;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project")
    private Set<Task> tasks = new HashSet<>();

    public Project(@NotNull String acronim) {
        this.acronim = acronim;
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }

    public Project() {
    }
}