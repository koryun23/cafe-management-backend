package com.cafe.dto.response.error;

import com.cafe.dto.response.ProductRegistrationResponseDto;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;

public class ErrorProductRegistrationResponsedto extends ProductRegistrationResponseDto {

    private List<String> errors;
    private HttpStatus httpStatus;

    public ErrorProductRegistrationResponsedto(List<String> errors, HttpStatus httpStatus) {
        this.errors = errors;
        this.httpStatus = httpStatus;
    }

    @Override
    public List<String> getErrors() {
        return errors;
    }

    @Override
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String toString() {
        return "ErrorProductRegistrationResponsedto{" +
                "errors=" + errors +
                ", httpStatus=" + httpStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ErrorProductRegistrationResponsedto that = (ErrorProductRegistrationResponsedto) o;
        return Objects.equals(errors, that.errors) && httpStatus == that.httpStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), errors, httpStatus);
    }
}
