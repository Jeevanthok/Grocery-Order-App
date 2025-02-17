package grocery.grocery_app.Service;

import grocery.grocery_app.Entity.User;
import grocery.grocery_app.Repo.UserRepo;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;


    // added static user and admin credential
    @PostConstruct
    @Transactional
    public void init() {
        if (userRepository.count() == 0) {

            User admin = new User();
            admin.setEmail("admin@example.com");
            admin.setName("Admin");
            admin.setPassword("admin123");
            admin.setRole("ADMIN");
            userRepository.save(admin);


            User user = new User();
            user.setEmail("user@example.com");
            user.setName("User");
            user.setPassword("user123");
            user.setRole("USER");
            userRepository.save(user);
        }
    }
}

