package grocery.grocery_app.Repo;

import grocery.grocery_app.Entity.GroceryOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<GroceryOrder, Long> {
}
