package com.cafe.dto;

import com.cafe.entity.table.CafeTableStatusType;

import java.util.Objects;

public class CafeTableRegistrationRequestDto {

    private CafeTableStatusType cafeTableStatusType;
    private Integer numberOfSeats;
    private String code;

    public CafeTableRegistrationRequestDto(CafeTableStatusType cafeTableStatusType,
                                           Integer numberOfSeats,
                                           String code) {
        this.cafeTableStatusType = cafeTableStatusType;
        this.numberOfSeats = numberOfSeats;
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
        CafeTableRegistrationRequestDto that = (CafeTableRegistrationRequestDto) o;
        return cafeTableStatusType == that.cafeTableStatusType &&
                Objects.equals(numberOfSeats, that.numberOfSeats) &&
                Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cafeTableStatusType, numberOfSeats, code);
    }

    @Override
    public String toString() {
        return "CafeTableRegistrationRequestDto{" +
                "cafeTableStatusType=" + cafeTableStatusType +
                ", numberOfSeats=" + numberOfSeats +
                ", code=" + code +
                '}';
    }
}
