package pl.management.project.project_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.management.project.project_management.model.entity.Role;
import pl.management.project.project_management.model.entity.User;
import pl.management.project.project_management.model.form.PasswordChangeForm;
import pl.management.project.project_management.repository.RoleRepository;
import pl.management.project.project_management.repository.UserRepository;

import java.util.Set;


@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        Role role = roleRepository.findOneRoleById(2);
        System.out.println(role);
        Set<Role> roles = user.getRoles();
        roles.add(role);
        user.setRoles(roles);
        System.out.println(user.getRoles());
        user.setActive(true);
        System.out.println(user);
        return userRepository.save(user);
    }

    public User getUser(String email){
        User user = userRepository.findByEmail(email);
        return user;
    }

    public User changePassword(PasswordChangeForm passwordChangeForm, Long id){
        // metoda Hibernae do modyfikacji usera
        User modifiedUser = userRepository.getOne(id);
        // kodowanie hasła
        // przepisanie wartości hasła
        modifiedUser.setPassword(bCryptPasswordEncoder.encode(passwordChangeForm.getPassword1()));
        // update
        return userRepository.save(modifiedUser);
    }

}
