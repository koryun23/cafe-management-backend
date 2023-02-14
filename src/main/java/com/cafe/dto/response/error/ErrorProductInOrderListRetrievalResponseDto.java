package com.cafe.dto.response.error;

import com.cafe.dto.response.ProductInOrderListRetrievalResponseDto;
import com.cafe.dto.response.ProductInOrderRetrievalResponseDto;
import io.jsonwebtoken.lang.Assert;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;

public class ErrorProductInOrderListRetrievalResponseDto extends ProductInOrderListRetrievalResponseDto {

    private List<String> errors;
    private HttpStatus httpStatus;
    public ErrorProductInOrderListRetrievalResponseDto(List<String> errors, HttpStatus httpStatus) {
        setErrors(errors);
        setHttpStatus(httpStatus);
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        Assert.notNull(errors, "Error list should not be null");
        this.errors = errors;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public void setHttpStatus(HttpStatus httpStatus) {
        Assert.notNull(httpStatus, "HttpStatus should not be null");
        this.httpStatus = httpStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ErrorProductInOrderListRetrievalResponseDto that = (ErrorProductInOrderListRetrievalResponseDto) o;
        return Objects.equals(errors, that.errors) && httpStatus == that.httpStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), errors, httpStatus);
    }

    @Override
    public String toString() {
        return "ErrorProductInOrderListRetrievalResponseDto{" +
                "errors=" + errors +
                ", httpStatus=" + httpStatus +
                '}';
    }
}
