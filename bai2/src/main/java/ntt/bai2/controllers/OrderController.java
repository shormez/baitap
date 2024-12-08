package ntt.bai2.controllers;

import ntt.bai2.entities.Orders;
import ntt.bai2.entities.OrderDetail;
import ntt.bai2.services.OrderDetailService;
import ntt.bai2.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;
@Controller
public class OrderController {
    @Autowired
    private OrdersService ordersService;
    private OrderDetailService orderDetailService;

    @GetMapping("/")
    public String viewHomePage(Model model){
        List<Orders> ordersLists = ordersService.listAll();
        model.addAttribute("orderLists", ordersLists);
        return "home";
    }

    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    public String viewOrderDetail(@PathVariable Long id,Model model){
        List<OrderDetail> orderDetailList= orderDetailService.findByOrderId(id);
     //   Order order= orderService.findById(id);
      //  model.addAttribute("order",order);
        model.addAttribute("orderDetailList",orderDetailList);

        return "order_detail";
    }

//    @RequestMapping(value = "/new",method = RequestMethod.GET)
//    public String newOrderAndOrderDetail(Model model){
//        model.a
//    }
}
