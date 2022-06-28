package com.cafe.entity.order;

import com.cafe.entity.product.ProductInOrder;
import com.cafe.entity.table.CafeTable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_ID_SEQUENCE")
    @SequenceGenerator(name = "ORDER_ID_SEQUENCE")
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<ProductInOrder> productsInOrder;

    @OneToOne
    @JoinColumn(name = "cafe_table_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "ORDER_CAFE_TABLE_ID"))
    private CafeTable table;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private OrderStatusType orderStatusType;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Order() {
    }

    public Order(CafeTable table, OrderStatusType orderStatusType, LocalDateTime createdAt) {
        this.table = table;
        this.orderStatusType = orderStatusType;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductInOrder> getProductsInOrder() {
        return productsInOrder;
    }

    public void setProductsInOrder(List<ProductInOrder> productsInOrder) {
        this.productsInOrder = productsInOrder;
    }

    public CafeTable getTable() {
        return table;
    }

    public void setTable(CafeTable table) {
        this.table = table;
    }

    public OrderStatusType getOrderStatusType() {
        return orderStatusType;
    }

    public void setOrderStatusType(OrderStatusType orderStatusType) {
        this.orderStatusType = orderStatusType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(productsInOrder, order.productsInOrder) && Objects.equals(table, order.table) && orderStatusType == order.orderStatusType && Objects.equals(createdAt, order.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productsInOrder, table, orderStatusType, createdAt);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                //", productsInOrder=" + productsInOrder +
                ", table=" + table +
                ", orderStatusType=" + orderStatusType +
                ", createdAt=" + createdAt +
                '}';
    }
}
