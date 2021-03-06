package jpabook.jpashop.api;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;

//    @GetMapping("/api/v1/orders")
//    public List<Order> ordersV1(){
//        List<Order> all = orderRepository.findAllByString(new OrderSearch());
//        for (Order order : all) {
//            order.getMember().getName();
//            order.getDelivery().getAddress();
//            List<OrderItem> orderItems = order.getOrderItems();
//
////            for (OrderItem orderItem : orderItems) {
////                orderItem.getItem().getName();
////            }
//            orderItems.stream().forEach(o -> o.getItem().getName());
//        }
//        return all;
//    }


    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAll(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName(); //Lazy 강제 초기화
            order.getDelivery().getAddress(); //Lazy 강제 초기환
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.stream().forEach(o -> o.getItem().getName()); //Lazy 강제 초기화
        }
        return all;
    }

}
