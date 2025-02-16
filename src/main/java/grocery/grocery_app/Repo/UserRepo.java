package grocery.grocery_app.Repo;

import grocery.grocery_app.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
