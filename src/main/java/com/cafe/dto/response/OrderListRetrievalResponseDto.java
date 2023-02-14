package com.cafe.dto.response;

import io.jsonwebtoken.lang.Assert;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;

public class OrderListRetrievalResponseDto {

    private List<OrderRetrievalResponseDto> orderRetrievalResponseDtoList;
    private HttpStatus httpStatus;

    public OrderListRetrievalResponseDto(List<OrderRetrievalResponseDto> orderRetrievalResponseDtoList, HttpStatus httpStatus) {
        setOrderRetrievalResponseDtoList(orderRetrievalResponseDtoList);
        setHttpStatus(httpStatus);
    }

    public OrderListRetrievalResponseDto() {
    }

    public List<OrderRetrievalResponseDto> getOrderRetrievalResponseDtoList() {
        return orderRetrievalResponseDtoList;
    }

    public void setOrderRetrievalResponseDtoList(List<OrderRetrievalResponseDto> orderRetrievalResponseDtoList) {
        Assert.notNull(orderRetrievalResponseDtoList, "Order retrieval response dto list should not be null");
        this.orderRetrievalResponseDtoList = orderRetrievalResponseDtoList;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        Assert.notNull(httpStatus, "Http status should not be null");
        this.httpStatus = httpStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderListRetrievalResponseDto that = (OrderListRetrievalResponseDto) o;
        return Objects.equals(orderRetrievalResponseDtoList, that.orderRetrievalResponseDtoList) && httpStatus == that.httpStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderRetrievalResponseDtoList, httpStatus);
    }

    @Override
    public String toString() {
        return "OrderListRetrievalResponseDto{" +
                "orderRetrievalResponseDtoList=" + orderRetrievalResponseDtoList +
                ", httpStatus=" + httpStatus +
                '}';
    }
}
