package com.cafe.dto.response.error;

import com.cafe.dto.response.OrderListRetrievalResponseDto;
import com.cafe.dto.response.OrderRetrievalResponseDto;
import io.jsonwebtoken.lang.Assert;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;

public class ErrorOrderListRetrievalResponseDto extends OrderListRetrievalResponseDto {
    private List<String> errors;
    private HttpStatus httpStatus;

    public ErrorOrderListRetrievalResponseDto(List<String> errors, HttpStatus httpStatus) {
        setErrors(errors);
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
        Assert.notNull(httpStatus, "http status should not be null");
        this.httpStatus = httpStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorOrderListRetrievalResponseDto that = (ErrorOrderListRetrievalResponseDto) o;
        return Objects.equals(errors, that.errors) &&
                Objects.equals(httpStatus, that.httpStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errors, httpStatus);
    }

    @Override
    public String toString() {
        return "ErrorOrderListRetrievalResponseDto{" +
                "errors=" + errors +
                ", httpStatus=" + httpStatus +
                '}';
    }
}
