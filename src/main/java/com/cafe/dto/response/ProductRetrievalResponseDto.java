package com.cafe.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

import java.util.Objects;

public class ProductRetrievalResponseDto {
    private Long id;
    private String name;
    private Integer amount;
    private Integer price;
    private HttpStatus httpStatus;

    public ProductRetrievalResponseDto() {
    }

    public ProductRetrievalResponseDto(Long id, String name, Integer amount, Integer price, HttpStatus httpStatus) {
        setId(id);
        setName(name);
        setAmount(amount);
        setPrice(price);
        setHttpStatus(httpStatus);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        Assert.notNull(id, "Product id should not be null");
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Assert.notNull(name, "Product name should not be null");
        Assert.hasText(name, "Product name should not be empty");
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        Assert.notNull(amount, "Product amount should not be null");
        this.amount = amount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        Assert.notNull(price, "Product price should not be null");
        this.price = price;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        Assert.notNull(httpStatus, "http status should not be null");
        this.httpStatus = httpStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRetrievalResponseDto that = (ProductRetrievalResponseDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(price, that.price) &&
                httpStatus == that.httpStatus &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount, price, httpStatus);
    }

    @Override
    public String toString() {
        return "ProductRetrievalResponseDto{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", httpStatus=" + httpStatus +
                ", id=" + id +
                '}';
    }
}
