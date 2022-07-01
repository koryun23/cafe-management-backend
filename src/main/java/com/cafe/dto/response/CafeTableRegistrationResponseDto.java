package com.cafe.dto.response;

import com.cafe.entity.table.CafeTableStatusType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class CafeTableRegistrationResponseDto {

    private CafeTableStatusType cafeTableStatusType;
    private Integer numberOfSeats;
    private String code;
    private LocalDateTime registeredAt;

    private List<String> errors;

    public CafeTableRegistrationResponseDto(CafeTableStatusType cafeTableStatusType,
                                            Integer numberOfSeats,
                                            String code,
                                            LocalDateTime registeredAt) {
        this.cafeTableStatusType = cafeTableStatusType;
        this.numberOfSeats = numberOfSeats;
        this.registeredAt = registeredAt;
        this.code = code;
    }

    public CafeTableRegistrationResponseDto() {
    }

    public CafeTableRegistrationResponseDto(List<String> errors) {
        this.errors = errors;
    }

    public CafeTableStatusType getCafeTableStatusType() {
        return cafeTableStatusType;
    }

    public void setCafeTableStatusType(CafeTableStatusType cafeTableStatusType) {
        this.cafeTableStatusType = cafeTableStatusType;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CafeTableRegistrationResponseDto that = (CafeTableRegistrationResponseDto) o;
        return cafeTableStatusType == that.cafeTableStatusType &&
                Objects.equals(numberOfSeats, that.numberOfSeats) &&
                Objects.equals(code, that.code) &&
                Objects.equals(registeredAt, that.registeredAt) &&
                Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cafeTableStatusType, numberOfSeats, code, registeredAt, errors);
    }

    @Override
    public String toString() {
        return "CafeTableRegistrationResponseDto{" +
                "cafeTableStatusType=" + cafeTableStatusType +
                ", numberOfSeats=" + numberOfSeats +
                ", code='" + code + '\'' +
                ", registeredAt=" + registeredAt +
                ", errors=" + errors +
                '}';
    }
}
