package pl.management.project.project_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.management.project.project_management.model.User;
import pl.management.project.project_management.service.UserService;


@Controller
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String home(Model model, Authentication auth){
        if(auth != null) {
            UserDetails principal = (UserDetails) auth.getPrincipal();
            model.addAttribute("principal", principal);
        }
        return "homePage";
    }

    @GetMapping("/login")
    public String login(Authentication auth, Model model) {
        if (auth != null) {
            UserDetails principal = (UserDetails) auth.getPrincipal();
            model.addAttribute("principal", principal);
        }
        return "loginForm";
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
