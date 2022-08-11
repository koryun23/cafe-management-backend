package com.cafe.dto.response;

import io.jsonwebtoken.lang.Assert;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;

public class AllCafeTablesRetrievalResponseDto {

    private List<CafeTableRetrievalResponseDto> cafeTableRetrievalResponseDtoList;
    private HttpStatus httpStatus;

    public AllCafeTablesRetrievalResponseDto() {
    }

    public AllCafeTablesRetrievalResponseDto(List<CafeTableRetrievalResponseDto> cafeTableRetrievalResponseDtoList, HttpStatus httpStatus) {
        setCafeTableRetrievalResponseDtoList(cafeTableRetrievalResponseDtoList);
        setHttpStatus(httpStatus);
    }

    public List<CafeTableRetrievalResponseDto> getCafeTableRetrievalResponseDtoList() {
        return cafeTableRetrievalResponseDtoList;
    }

    public void setCafeTableRetrievalResponseDtoList(List<CafeTableRetrievalResponseDto> cafeTableRetrievalResponseDtoList) {
        Assert.notNull(cafeTableRetrievalResponseDtoList, "Cafe table retrieval response dto list should not be null");
        this.cafeTableRetrievalResponseDtoList = cafeTableRetrievalResponseDtoList;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        Assert.notNull(httpStatus, "http status should not be null");
        this.httpStatus = httpStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllCafeTablesRetrievalResponseDto that = (AllCafeTablesRetrievalResponseDto) o;
        return Objects.equals(cafeTableRetrievalResponseDtoList, that.cafeTableRetrievalResponseDtoList) && httpStatus == that.httpStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cafeTableRetrievalResponseDtoList, httpStatus);
    }

    @Override
    public String toString() {
        return "AllCafeTablesRetrievalResponseDto{" +
                "cafeTableRetrievalResponseDtoList=" + cafeTableRetrievalResponseDtoList +
                ", httpStatus=" + httpStatus +
                '}';
    }
}
