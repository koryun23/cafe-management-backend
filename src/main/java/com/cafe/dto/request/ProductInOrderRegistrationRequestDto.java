package com.cafe.dto.request;

import java.util.Objects;

public class ProductInOrderRegistrationRequestDto {

    private String productName;
    private Long orderId;
    private Integer amount;
    private String waiterUsername;

    public ProductInOrderRegistrationRequestDto(String productName,
                                                Long orderId,
                                                Integer amount, String waiterName) {
        this.productName = productName;
        this.orderId = orderId;
        this.amount = amount;
        this.waiterUsername = waiterName;
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

    public String getWaiterUsername() {
        return waiterUsername;
    }

    public void setWaiterUsername(String waiterUsername) {
        this.waiterUsername = waiterUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInOrderRegistrationRequestDto that = (ProductInOrderRegistrationRequestDto) o;
        return Objects.equals(productName, that.productName) &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(waiterUsername, that.waiterUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, orderId, amount, waiterUsername);
    }

    @Override
    public String toString() {
        return "ProductInOrderRegistrationRequestDto{" +
                "productName='" + productName + '\'' +
                ", orderId=" + orderId +
                ", amount=" + amount +
                ", waiterUsername=" + waiterUsername +
                '}';
    }
}
