package pl.management.project.project_management.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private int id;
    private String role;


}