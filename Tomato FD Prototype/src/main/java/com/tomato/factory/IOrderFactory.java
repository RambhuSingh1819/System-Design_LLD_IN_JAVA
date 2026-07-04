package com.tomato.factory;

import com.tomato.models.Menu;
import com.tomato.models.Restaurant;
import com.tomato.models.User;
import com.tomato.order.Order;
import com.tomato.strategy.IPaymentStrategy;
import java.util.List;

public interface IOrderFactory {
  
    Order createOrder(String type, User user,
                      Restaurant restaurant,
                      List<Menu> items,
                      IPaymentStrategy strategy);
  }
