package com.cafe.dto.response;

import com.cafe.entity.table.CafeTableStatusType;
import io.jsonwebtoken.lang.Assert;
import org.springframework.http.HttpStatus;

import java.util.Objects;

public class CafeTableRetrievalResponseDto {
    private Long id;
    private String code;
    private Integer seats;
    private CafeTableStatusType status;
    private HttpStatus httpStatus;

    public CafeTableRetrievalResponseDto(Long id, String code, Integer seats, CafeTableStatusType status, HttpStatus httpStatus) {
        setId(id);
        setCode(code);
        setSeats(seats);
        setStatus(status);
        setHttpStatus(httpStatus);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        Assert.notNull(id, "Table id should not be null");
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        Assert.notNull(code, "Table code should not be null");
        Assert.hasText(code, "Table code should not be empty");
        this.code = code;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        Assert.notNull(seats, "Table seats should not be null");
        this.seats = seats;
    }

    public CafeTableStatusType getStatus() {
        return status;
    }

    public void setStatus(CafeTableStatusType status) {
        Assert.notNull(status, "Cafe table status type should not be null");
        this.status = status;
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
        CafeTableRetrievalResponseDto that = (CafeTableRetrievalResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(code, that.code) && Objects.equals(seats, that.seats) && status == that.status && httpStatus == that.httpStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, seats, status, httpStatus);
    }

    @Override
    public String toString() {
        return "CafeTableRetrievalResponseDto{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", seats=" + seats +
                ", status=" + status +
                ", httpStatus=" + httpStatus +
                '}';
    }
}
