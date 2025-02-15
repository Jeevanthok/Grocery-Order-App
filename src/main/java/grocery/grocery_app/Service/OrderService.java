package grocery.grocery_app.Service;

import grocery.grocery_app.Entity.GroceryItem;
import grocery.grocery_app.Entity.GroceryOrder;
import grocery.grocery_app.Repo.GroceryItemRepository;
import grocery.grocery_app.Repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    public GroceryOrder createOrder(Map<Long,Integer> groceryItemIds) {
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
                throw new IllegalArgumentException("Insufficient inventory for item: " + item.getName());
            }
            item.setInventory(updatedInventory);
            groceryItemRepository.save(item);
        }

        GroceryOrder order = new GroceryOrder();
      //  order.setGroceryItems(groceryItems);
        order.setTotalAmount(totalAmount);
        order.setQuantity(total_productcount);
        return orderRepository.save(order);
    }
}
