package pl.management.project.project_management;

import lombok.Data;
import pl.management.project.project_management.model.User;

import javax.persistence.*;


@Data
@Entity
public class Project{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private String administrator;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}