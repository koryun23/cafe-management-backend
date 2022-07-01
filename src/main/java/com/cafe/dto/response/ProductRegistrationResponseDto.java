package com.cafe.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ProductRegistrationResponseDto {

    private String name;
    private Integer price;
    private Integer amount;
    private LocalDateTime registeredAt;

    private List<String> errors;

    public ProductRegistrationResponseDto(String name, Integer price, Integer amount, LocalDateTime registeredAt) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.registeredAt = registeredAt;
    }

    public ProductRegistrationResponseDto(List<String> errors) {
        this.errors = errors;
    }

    public ProductRegistrationResponseDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRegistrationResponseDto that = (ProductRegistrationResponseDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(registeredAt, that.registeredAt) &&
                Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, amount, registeredAt, errors);
    }

    @Override
    public String toString() {
        return "ProductRegistrationResponseDto{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", registeredAt=" + registeredAt +
                ", errors=" + errors +
                '}';
    }
}
