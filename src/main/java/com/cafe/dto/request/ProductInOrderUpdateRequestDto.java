package com.cafe.dto.request;

import com.cafe.entity.product.ProductInOrderStatusType;

import java.util.Objects;

public class ProductInOrderUpdateRequestDto {

    private Long id;
    private String productName;
    private Long orderId;
    private Integer amount;
    private String waiterUsername;
    private ProductInOrderStatusType status;

    public ProductInOrderUpdateRequestDto(Long id,
                                          String productName,
                                          Long orderId,
                                          Integer amount,
                                          String waiterUsername,
                                          ProductInOrderStatusType status) {
        this.id = id;
        this.productName = productName;
        this.orderId = orderId;
        this.amount = amount;
        this.waiterUsername = waiterUsername;
        this.status = status;
    }

    public ProductInOrderUpdateRequestDto() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWaiterUsername() {
        return waiterUsername;
    }

    public void setWaiterUsername(String waiterUsername) {
        this.waiterUsername = waiterUsername;
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
        ProductInOrderUpdateRequestDto that = (ProductInOrderUpdateRequestDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(waiterUsername, that.waiterUsername) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, orderId, amount, waiterUsername, status);
    }

    @Override
    public String toString() {
        return "ProductInOrderUpdateRequestDto{" +
                "id=" + id +
                ", productName=" + productName +
                ", orderId=" + orderId +
                ", amount=" + amount +
                ", waiterUsername=" + waiterUsername +
                ", status=" + status +
                '}';
    }
}
