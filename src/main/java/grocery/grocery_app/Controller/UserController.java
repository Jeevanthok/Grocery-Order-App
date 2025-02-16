package grocery.grocery_app.Controller;

import grocery.grocery_app.Entity.GroceryOrder;
import grocery.grocery_app.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/orders")
public class UserController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/userid/{user_id}")
    public GroceryOrder createOrder(@RequestBody Map<Long,Integer> orderdetails,@PathVariable int user_id) {
        return orderService.createOrder(orderdetails,user_id);
    }
}
