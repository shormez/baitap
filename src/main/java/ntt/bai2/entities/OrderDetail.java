package ntt.bai2.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_detail")
@Getter
@Setter
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

    public OrderDetail(long productId, long quantity, double unitPrice, long lineItemId) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lineItemId = lineItemId;
    }
}
