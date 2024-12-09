package ntt.bai2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

//import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "orders")
@Getter
@Setter
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;

    private String orderMode;
    private long customerId;
    private long orderStatus;
    private long orderTotal;
    private long salesRepId;
    private long promotionId;
    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderDetail> orderDetaillist=new ArrayList<>();
}
