package pl.management.project.project_management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.management.project.project_management.model.User;
import pl.management.project.project_management.service.UserService;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model) {
        System.out.println(user);
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            return "registration";
        } else {
            userService.saveUser(user);
            System.out.println(user);
            String successMessage = "User has been registered successfully";
            model.addAttribute("successMessage", successMessage);
            model.addAttribute("user", new User());
        }
        return "registration";
    }

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("AUTH ma wartosc: "+auth);
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("login", "Welcome " + user.getDisplayedName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

}
