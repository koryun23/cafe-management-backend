package com.cafe.service.core.product;

import java.util.Objects;

public class ProductInOrderCreationParams {

    private String productName;

    private Long orderId;

    private Integer amount;

    public ProductInOrderCreationParams(String productName, Long orderId, Integer amount) {
        this.productName = productName;
        this.orderId = orderId;
        this.amount = amount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInOrderCreationParams that = (ProductInOrderCreationParams) o;
        return Objects.equals(productName, that.productName) && Objects.equals(orderId, that.orderId) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, orderId, amount);
    }

    @Override
    public String toString() {
        return "ProductInOrderCreationParams{" +
                "productName='" + productName + '\'' +
                ", orderId=" + orderId +
                ", amount=" + amount +
                '}';
    }
}
