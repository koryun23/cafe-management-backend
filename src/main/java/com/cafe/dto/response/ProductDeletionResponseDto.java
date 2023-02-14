package com.cafe.dto.response;

import io.jsonwebtoken.lang.Assert;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;

public class ProductDeletionResponseDto {

    private HttpStatus httpStatus;
    private List<String> errors;

    public ProductDeletionResponseDto(HttpStatus httpStatus) {
        setHttpStatus(httpStatus);
    }

    public ProductDeletionResponseDto(HttpStatus httpStatus, List<String> errors) {
        setHttpStatus(httpStatus);
        this.errors = errors;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        Assert.notNull(httpStatus, "Http status should not be null");
        this.httpStatus = httpStatus;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        Assert.notNull(errors, "Error list should not be null");
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDeletionResponseDto that = (ProductDeletionResponseDto) o;
        return httpStatus == that.httpStatus && errors == that.errors;
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpStatus, errors);
    }

    @Override
    public String toString() {
        return "ProductDeletionResponseDto{" +
                "httpStatus=" + httpStatus +
                "errors=" + errors +
                '}';
    }
}
