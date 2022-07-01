package com.cafe.dto.response.error;

import com.cafe.dto.response.OrderRegistrationResponseDto;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;

public class ErrorOrderRegistrationResponseDto extends OrderRegistrationResponseDto {

    private List<String> errors;
    private HttpStatus status;

    public ErrorOrderRegistrationResponseDto(List<String> errors, HttpStatus status) {
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
        ErrorOrderRegistrationResponseDto that = (ErrorOrderRegistrationResponseDto) o;
        return Objects.equals(errors, that.errors) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), errors, status);
    }

    @Override
    public String toString() {
        return "ErrorOrderRegistrationResponseDto{" +
                "errors=" + errors +
                ", status=" + status +
                '}';
    }
}
