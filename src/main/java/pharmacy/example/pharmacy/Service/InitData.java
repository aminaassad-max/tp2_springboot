package pharmacy.example.pharmacy.Service;

import pharmacy.example.pharmacy.entity.Role;
import pharmacy.example.pharmacy.entity.User;
import pharmacy.example.pharmacy.repository.RoleRepository;
import pharmacy.example.pharmacy.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public InitData(RoleRepository roleRepository,
                    UserRepository userRepository,
                    PasswordEncoder passwordEncoder) {

        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) {

        if (roleRepository.count() == 0) {

            Role admin = roleRepository.save(new Role("ADMIN"));
            Role pharmacien = roleRepository.save(new Role("PHARMACIEN"));
            Role caissier = roleRepository.save(new Role("CAISSIER"));
            Role client = roleRepository.save(new Role("CLIENT"));


            userRepository.save(new User(
                    "admin",
                    passwordEncoder.encode("admin123"),
                    admin
            ));


            userRepository.save(new User(
                    "pharma",
                    passwordEncoder.encode("pharma123"),
                    pharmacien
            ));


            userRepository.save(new User(
                    "caisse",
                    passwordEncoder.encode("caisse123"),
                    caissier
            ));


            userRepository.save(new User(
                    "client",
                    passwordEncoder.encode("client123"),
                    client
            ));
        }
    }
}