package ntt.bai2.controllers;

import ntt.bai2.entities.Orders;
import ntt.bai2.entities.OrderDetail;
import ntt.bai2.services.OrderDetailService;
import ntt.bai2.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@Controller
public class OrderController {
    @Autowired
    private OrdersService ordersService;
    private OrderDetailService orderDetailService;
    private List<OrderDetail> dsach;
    @GetMapping("/")
    public String viewHomePage(Model model){
        List<Orders> ordersLists = ordersService.listAll();
        model.addAttribute("ordersLists", ordersLists);
        return "home";
    }

    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    public String viewOrderDetail(@PathVariable Long id,Model model){
        List<OrderDetail> orderDetailList= ordersService.findById(id).getOrderDetaillist();

     //   Order order= orderService.findById(id);
      //  model.addAttribute("order",order);
        model.addAttribute("orderDetailList",orderDetailList);

        return "order_detail";
    }

    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public String newOrder(Model model){
        dsach=new ArrayList<>();
        Orders orders=new Orders();

     //   OrderDetail orderDetail=new OrderDetail();


        model.addAttribute("orders",orders);
      //  model.addAttribute("order_Detail",orderDetail);
        return "new_order";

    }


    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public String saveOrder(@ModelAttribute("orders") Orders orders ){
        System.out.println("id tai save "+orders.getId());
        orders.getOrderDetaillist().addAll(dsach);
       // orderDetailService.save(orderDetail);
        ordersService.save(orders);

        return "redirect:/";
    }
    @RequestMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id){
        ordersService.delete(id);
        return "redirect:/";
    }
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String editOrderAndOrderDetail(@PathVariable Long id,Model model){
        dsach=new ArrayList<>();
        Orders existingOrders= ordersService.findById(id);

        model.addAttribute("orders",existingOrders);
        System.out.println("id boc duoc tai edit "+existingOrders.getId());

        model.addAttribute("orderDetailList",existingOrders.getOrderDetaillist());
        return "edit_order";
    }
    @PostMapping("/addRow/{id}")
    public String addRow(@PathVariable Long id, Model model) {

        int newLineItemId =1;
        Orders orders= ordersService.findById(id);
        System.out.println("id tai addrow "+orders.getId());
//        if(orders.getOrderDetaillist()!=null ){
//            newLineItemId+= orders.getOrderDetaillist().size();
//        }
        OrderDetail newDetail = new OrderDetail(0, 0, 0.0, newLineItemId);
        dsach.add(newDetail);
        System.out.println(orders.getOrderDate());
    //    orders.getOrderDetaillist().add(newDetail);
        System.out.println("kich ko orderdetaillist "+orders.getOrderDetaillist().size());
        model.addAttribute("orders", orders);
        return "redirect:/edit/"+id;
    }

}
