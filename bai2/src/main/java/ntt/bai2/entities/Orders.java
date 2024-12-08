package ntt.bai2.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date orderDate;
    private String orderMode;
    private long customerId;
    private long orderStatus;
    private long orderTotal;
    private long salesRepId;
    private long promotionId;
    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderDetail> orderDetaillist;
}
