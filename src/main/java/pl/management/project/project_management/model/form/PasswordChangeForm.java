package pl.management.project.project_management.model.form;

import javax.validation.constraints.Size;

public class PasswordChangeForm {
    @Size(min = 6, max = 20, message = "hasło musi mieć od {min} do {max} znaków")
    private String password1;
    @Size(min = 6, max = 20, message = "hasło musi mieć od {min} do {max} znaków")
    private String password2;

    public PasswordChangeForm() { }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
