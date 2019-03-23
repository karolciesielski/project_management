package pl.management.project.project_management.model.form;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class PasswordChangeForm {
    @Size(min = 6, max = 20, message = "hasło musi mieć od {min} do {max} znaków")
    private String password1;
    @Size(min = 6, max = 20, message = "hasło musi mieć od {min} do {max} znaków")
    private String password2;

    public PasswordChangeForm() { }
}
