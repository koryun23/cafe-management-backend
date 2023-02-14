package com.cafe.dto.request;

import java.util.Objects;

public class ProductDeletionRequestDto {
    private Long productId;

    public ProductDeletionRequestDto() {
    }

    public ProductDeletionRequestDto(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDeletionRequestDto that = (ProductDeletionRequestDto) o;
        return Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    @Override
    public String toString() {
        return "ProductDeletionRequestDto{" +
                "productId=" + productId +
                '}';
    }
}
