package pl.management.project.project_management.model.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegisterUserForm {
    @NotBlank(message="Email nie może być pusty")
    @Email(message = "Niepoprawny format adresu email")
    private String email;
    @Size(min=6, max=20, message = "Hasło musi mieć do {min} do {max} znaków")
    private String password;
    @NotBlank(message = "Imię nie może być puste")
    private String firstname;
    @NotBlank(message = "Nazwisko nie może być puste")
    private String lastname;
}
