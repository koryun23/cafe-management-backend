package com.cafe.dto;

import com.cafe.entity.table.CafeTableStatusType;

import java.time.LocalDateTime;
import java.util.Objects;

public class CafeTableRegistrationResponseDto {

    private CafeTableStatusType cafeTableStatusType;
    private Integer numberOfSeats;
    private String code;
    private LocalDateTime registeredAt;

    public CafeTableRegistrationResponseDto(CafeTableStatusType cafeTableStatusType,
                                            Integer numberOfSeats,
                                            String code,
                                            LocalDateTime registeredAt) {
        this.cafeTableStatusType = cafeTableStatusType;
        this.numberOfSeats = numberOfSeats;
        this.registeredAt = registeredAt;
        this.code = code;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CafeTableRegistrationResponseDto that = (CafeTableRegistrationResponseDto) o;
        return cafeTableStatusType == that.cafeTableStatusType && Objects.equals(numberOfSeats, that.numberOfSeats) && Objects.equals(code, that.code) && Objects.equals(registeredAt, that.registeredAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cafeTableStatusType, numberOfSeats, code, registeredAt);
    }

    @Override
    public String toString() {
        return "CafeTableRegistrationResponseDto{" +
                "cafeTableStatusType=" + cafeTableStatusType +
                ", numberOfSeats=" + numberOfSeats +
                ", code='" + code + '\'' +
                ", registeredAt=" + registeredAt +
                '}';
    }
}
