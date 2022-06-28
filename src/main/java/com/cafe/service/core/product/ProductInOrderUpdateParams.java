package com.cafe.service.core.product;

import com.cafe.entity.product.ProductInOrderStatusType;

import java.util.Objects;

public class ProductInOrderUpdateParams {
    private Long id;
    private Long productId;
    private Long orderId;
    private Integer amount;
    private ProductInOrderStatusType status;

    public ProductInOrderUpdateParams(Long id,
                                      Long productId,
                                      Long orderId,
                                      Integer amount,
                                      ProductInOrderStatusType status) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ProductInOrderStatusType getStatus() {
        return status;
    }

    public void setStatus(ProductInOrderStatusType status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInOrderUpdateParams that = (ProductInOrderUpdateParams) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, orderId, amount, status);
    }

    @Override
    public String toString() {
        return "ProductInOrderUpdateParams{" +
                "id=" + id +
                ", productId=" + productId +
                ", orderId=" + orderId +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }
}
