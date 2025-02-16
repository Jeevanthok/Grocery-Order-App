package grocery.grocery_app.Repo;


import grocery.grocery_app.Entity.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User,Integer> {

    @Query("select u from User u  where u.email =:email")
    public User getUserByName(@Param("email") String email);
}
