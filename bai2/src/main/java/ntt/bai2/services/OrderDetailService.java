package ntt.bai2.services;

import ntt.bai2.entities.OrderDetail;
import ntt.bai2.repositories.OrderDetailRepository;


import java.util.List;

public class OrderDetailService {
    private OrderDetailRepository repo;

    public List<OrderDetail> findAll(){
        return repo.findAll();
    }
    public OrderDetail findById(Long id){
        return repo.findById(id).orElse(null);
    }
    public List<OrderDetail> findByOrderId(Long orderId){
        return repo.findByOrders_Id(orderId).orElse(null);
    }

    public void save(OrderDetail orderDetail){
        repo.save(orderDetail);
    }
    public void delete(Long id){
        repo.deleteById(id);
    }
}
