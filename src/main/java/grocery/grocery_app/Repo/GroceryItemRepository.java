package grocery.grocery_app.Repo;

import grocery.grocery_app.Entity.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long> {
    List<GroceryItem> findByNameContaining(String name);
}
