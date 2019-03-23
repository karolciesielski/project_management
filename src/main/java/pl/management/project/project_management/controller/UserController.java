package pl.management.project.project_management.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.management.project.project_management.model.User;
import pl.management.project.project_management.model.form.PasswordChangeForm;
import pl.management.project.project_management.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/changePassword")
    public String changePassword(Model model, Authentication auth){
        if(auth != null) {
            UserDetails principal = (UserDetails) auth.getPrincipal();
            model.addAttribute("principal", principal);
        }
        // create UserPasswordForm
        PasswordChangeForm passwordChangeForm = new PasswordChangeForm();
        model.addAttribute("passwordChangeForm",passwordChangeForm);
        return "changePassword";
    }
    @PostMapping("/changePassword")
    public String changePassword(@ModelAttribute @Valid PasswordChangeForm passwordChangeForm,
                                 BindingResult bindingResult,
                                 Authentication auth,
                                 Model model){
        if(bindingResult.hasErrors()){
            return "changePassword";
        }
        if(auth != null) {
            UserDetails principal = (UserDetails) auth.getPrincipal();
            model.addAttribute("principal", principal);
        }
        UserDetails loggedUser = (UserDetails) auth.getPrincipal();
        // logged in on currentEmail
        String currentEmail = loggedUser.getUsername();
        // give back user - object user who's logged in
        User currentUser = userService.getUser(currentEmail);
        System.out.println("aktualne: "+currentUser.getPassword());
        System.out.println("zmienione: "+passwordChangeForm.getPassword1());
        // update
        userService.changePassword(passwordChangeForm, currentUser.getId());
        return "changePassword";
    }
}
