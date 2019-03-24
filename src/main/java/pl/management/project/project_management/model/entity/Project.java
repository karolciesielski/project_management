package pl.management.project.project_management.model.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Project{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String administrator;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}