package ntt.bai2.repositories;

import ntt.bai2.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    Optional<List<OrderDetail>> findByOrders_Id(Long orderId);
}
