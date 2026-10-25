package pharmacy.example.pharmacy.Controller;

import pharmacy.example.pharmacy.entity.User;
import pharmacy.example.pharmacy.repository.UserRepository;
import pharmacy.example.pharmacy.repository.RoleRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public AdminUserController(UserRepository userRepository,
                               RoleRepository roleRepository) {

        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    // afficher les utilisateurs
    @GetMapping
    public String users(Model model) {

        model.addAttribute("users",
                userRepository.findAll());

        return "admin/users";
    }


    // page ajout utilisateur
    @GetMapping("/add")
    public String addUser(Model model) {

        model.addAttribute("user", new User());

        model.addAttribute("roles",
                roleRepository.findAll());

        return "admin/add-user";
    }


    // enregistrer utilisateur
    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user) {

        userRepository.save(user);

        return "redirect:/admin/users";
    }


    // supprimer utilisateur
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {

        userRepository.deleteById(id);

        return "redirect:/admin/users";
    }

}