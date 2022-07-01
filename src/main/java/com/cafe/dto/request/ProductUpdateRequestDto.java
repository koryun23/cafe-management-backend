package com.cafe.dto.request;

import java.util.Objects;

public class ProductUpdateRequestDto {

    private String originalName;
    private String name;
    private Integer price;
    private Integer amount;

    public ProductUpdateRequestDto(String originalName, String name, Integer price, Integer amount) {
        this.originalName = originalName;
        this.name = name;
        this.price = price;
        this.amount = amount;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductUpdateRequestDto that = (ProductUpdateRequestDto) o;
        return Objects.equals(originalName, that.originalName) &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originalName, name, price, amount);
    }

    @Override
    public String toString() {
        return "ProductUpdateRequestDto{" +
                "originalName=" + originalName +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
