package com.cafe.dto.response.error;

import com.cafe.dto.response.OrderUpdateResponseDto;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;

public class ErrorOrderUpdateResponseDto extends OrderUpdateResponseDto {

    private List<String> errors;
    private HttpStatus status;

    public ErrorOrderUpdateResponseDto(List<String> errors, HttpStatus status) {
        this.errors = errors;
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ErrorOrderUpdateResponseDto that = (ErrorOrderUpdateResponseDto) o;
        return Objects.equals(errors, that.errors) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), errors, status);
    }

    @Override
    public String toString() {
        return "ErrorOrderUpdateResponseDto{" +
                "errors=" + errors +
                ", status=" + status +
                '}';
    }
}
