package grocery.grocery_app.Controller;

import grocery.grocery_app.Entity.GroceryItem;
import grocery.grocery_app.Service.GroceryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/grocery-items")
public class AdminController {

    @Autowired
    private GroceryItemService groceryItemService;

    //add grocery item
    @PostMapping("/additem")
    public GroceryItem addGroceryItem(@RequestBody GroceryItem groceryItem) {
        return groceryItemService.addItem(groceryItem);
    }
    // get all item
    @GetMapping("/getAllitem")
    public List<GroceryItem> getAllGroceryItems() {
        return groceryItemService.getAllItems();
    }

    // delete item
    @DeleteMapping("delete/{id}")
    public void removeGroceryItem(@PathVariable Long id) {
        groceryItemService.removeItem(id);
    }
   // update item
    @PutMapping("update/{id}")
    public GroceryItem updateGroceryItem(@PathVariable Long id, @RequestBody GroceryItem groceryItem) {
        return groceryItemService.updateItem(id, groceryItem);
    }
}
