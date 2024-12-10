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
        model.addAttribute("dsach",dsach);
      //  model.addAttribute("order_Detail",orderDetail);
        return "new_order";

    }


    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public String saveOrder(@ModelAttribute("orders") Orders orders ){
        System.out.println("id tai save "+orders.getId());
      //  orders.getOrderDetaillist().addAll(dsach);
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
        System.out.println("id boc duoc tai edit "+id);
        Orders existingOrders= ordersService.findById(id);
//        if (existingOrders == null) {
//            // Xử lý nếu không tìm thấy đơn hàng
//            System.out.println("bi null");
//            return "error"; // trả về trang lỗi
//        }
        System.out.println(existingOrders.getId()+" "+existingOrders.getOrderDate()+" "+existingOrders.getOrderTotal());
        List<OrderDetail> orderDetaillist = existingOrders.getOrderDetaillist();
        if (orderDetaillist == null) {
            orderDetaillist = new ArrayList<>();  // Khởi tạo danh sách trống nếu null
        }
        for(OrderDetail x: orderDetaillist){
            System.out.println(x.getId()+" "+x.getQuantity()+" "+x.getProductId()+" "+x.getLineItemId()+" "+x.getOrders().getId());
        }
        model.addAttribute("orders",existingOrders);


        model.addAttribute("orderDetailList",orderDetaillist);
      //  model.addAttribute("dsach",dsach);
        return "edit_order";
    }
    @GetMapping("/submit/{id}")
    public String addRow(@ModelAttribute("orders") Orders orders,@PathVariable Long id ,Model model,
                         @RequestParam("quantity[]")List<String>quantity,
                         @RequestParam("productId[]")List<String>productId,
                         @RequestParam("unitPrice[]")List<String>unitPrice,
                         @RequestParam("lineItemId[]")List<String>lineItemId) {

        int newLineItemId =1;
        System.out.println("id tai submit la "+id);
        System.out.println("id cua orders den la "+orders.getId());
        for(int i=0;i<quantity.size();i++){
            OrderDetail newOrderDetail=new OrderDetail(Long.parseLong(quantity.get(i)),Long.parseLong(productId.get(i)),Double.parseDouble(unitPrice.get(i)),Long.parseLong(lineItemId.get(i)));
            newOrderDetail.setOrders(orders);
            dsach.add(newOrderDetail);
            System.out.println(newOrderDetail.getId()+" "+newOrderDetail.getQuantity()+" "+newOrderDetail.getLineItemId());
           // orderDetailService.save(newOrderDetail);
            System.out.println("thuoc tinh trong orders "+quantity.get(i)+" "+productId.get(i)+" "+unitPrice.get(i)+" "+lineItemId.get(i));
        }
        Orders existedOrder= ordersService.findById(id);
        orders.getOrderDetaillist().addAll(dsach);
        System.out.println(orders.getOrderDate()+" "+orders.getOrderMode()+" "+orders.getCustomerId()+" "+orders.getOrderTotal());
     //   existedOrder.getOrderDetaillist().addAll(dsach);
     //   ordersService.save(existedOrder);
        ordersService.save(orders);
       // Orders orders= ordersService.findById(id);
      //  System.out.println("id order tai addrow "+id);
      //  System.out.println("id tai addrow "+orders.getId());
//        if(orders.getOrderDetaillist()!=null ){
//            newLineItemId+= orders.getOrderDetaillist().size();
//        }
//        OrderDetail newDetail = new OrderDetail(0, 0, 0.0, newLineItemId);
//        dsach.add(newDetail);
//
//        System.out.println(orders.getOrderDate());
//    //    orders.getOrderDetaillist().add(newDetail);
//        System.out.println("kich ko orderdetaillist "+orders.getOrderDetaillist().size());
//        model.addAttribute("orders", orders);
        return "redirect:/";
    }

}
