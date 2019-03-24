package pl.management.project.project_management.model.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Displayed Name may not be blank")
    private String displayedName;
    @NotBlank(message = "Login may not be blank")
    private String login;
    @Length(min = 6, message = "*Password must have min 6 chars")
//    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Password must have upper case, lower case and digit")
    @NotBlank(message = "Password may not be blank")
    private String password;
    @Email(message = "*Enter correct email address")
    @NotBlank(message = "Email may not be blank")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    List<Project> project;

    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Role> roles = new HashSet<>();

    private boolean active;
}