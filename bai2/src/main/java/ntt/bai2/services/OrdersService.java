package ntt.bai2.services;


import jakarta.transaction.Transactional;
import ntt.bai2.entities.Orders;
import ntt.bai2.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrdersService {
    @Autowired
    private OrderRepository repo;
    public List<Orders> listAll(){
        return repo.findAll();
    }
    public Orders findById(Long id){
        return repo.findById(id).orElse(null);
    }
    public void save(Orders orders){
        repo.save(orders);
    }
    public void delete(Long id){
        repo.deleteById(id);
    }
}
