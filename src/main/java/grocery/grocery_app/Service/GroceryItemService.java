package grocery.grocery_app.Service;

import grocery.grocery_app.Entity.GroceryItem;
import grocery.grocery_app.Repo.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class GroceryItemService {

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    // Add new grocery item
    public GroceryItem addItem(GroceryItem groceryItem) {
        return groceryItemRepository.save(groceryItem);
    }

    // Get all grocery items
    public List<GroceryItem> getAllItems() {
        return groceryItemRepository.findAll();
    }

    // Remove grocery item
    public void removeItem(Long id) {
        groceryItemRepository.deleteById(id);
    }

    // Update grocery item
    public GroceryItem updateItem(Long id, GroceryItem groceryItemDetails) {
        Optional<GroceryItem> optionalGroceryItem = groceryItemRepository.findById(id);
        if (optionalGroceryItem.isPresent()) {
            GroceryItem groceryItem = optionalGroceryItem.get();
            groceryItem.setName(groceryItemDetails.getName());
            groceryItem.setDescription(groceryItemDetails.getDescription());
            groceryItem.setPrice(groceryItemDetails.getPrice());
            groceryItem.setInventory(groceryItemDetails.getInventory());
            return groceryItemRepository.save(groceryItem);
        }
        return null;
    }
}

