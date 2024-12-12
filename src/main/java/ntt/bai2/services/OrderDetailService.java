package ntt.bai2.services;

import ntt.bai2.entities.OrderDetail;
import ntt.bai2.repositories.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository repo;

    public List<OrderDetail> findAll(){
        return repo.findAll();
    }
    public OrderDetail findById(Long id){
        return repo.findById(id).orElse(null);
    }


    public void save(OrderDetail orderDetail){
        repo.save(orderDetail);
    }
    public void delete(Long id){
        repo.deleteById(id);
    }
}
