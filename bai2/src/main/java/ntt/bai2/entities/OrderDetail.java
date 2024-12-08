package ntt.bai2.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name ="order_id")
    private Orders orders;

    private long productId;
    private long quantity;
    private double unitPrice;
    private long lineItemId;


}
