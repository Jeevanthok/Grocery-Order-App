package grocery.grocery_app.Service;

import grocery.grocery_app.Entity.GroceryItem;
import grocery.grocery_app.Entity.GroceryOrder;
import grocery.grocery_app.Entity.User;
import grocery.grocery_app.Exception.ResourceNotFound;
import grocery.grocery_app.Repo.GroceryItemRepository;
import grocery.grocery_app.Repo.OrderRepository;
import grocery.grocery_app.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    @Autowired
    private UserRepo userRepo;

    public GroceryOrder createOrder(Map<Long,Integer> groceryItemIds, int user_id) {

        User user = userRepo.findById(user_id)
                .orElseThrow(() -> new ResourceNotFound("User", "id", user_id));;
        List<Long> itemlst = new ArrayList<>(groceryItemIds.keySet());
        List<GroceryItem> groceryItems = groceryItemRepository.findAllById(itemlst);
        int total_productcount = (int) itemlst.stream().count();
        double totalAmount = groceryItems.stream()
                .mapToDouble(item -> item.getPrice() * groceryItemIds.get(item.getId()))
                .sum();
        for (GroceryItem item: groceryItems){
            int quantityOrdered = groceryItemIds.get(item.getId());
            int updatedInventory = item.getInventory() - quantityOrdered;
            if (updatedInventory < 0) {
                throw new ResourceNotFound("Insufficient inventory for item: ","id", Math.toIntExact(item.getId()));
            }

            item.setInventory(updatedInventory);
            groceryItemRepository.save(item);
        }

        GroceryOrder order = new GroceryOrder();
      //  order.setGroceryItems(groceryItems);
        order.setTotalAmount(totalAmount);
        order.setQuantity(total_productcount);
        order.setUser(user);
        return orderRepository.save(order);
    }
}
