package com.example.microservices.projections;

/**
 * Projection for {@link com.example.microservices.models.Product}
 */
public interface ProductInfo {
    String getTitle();

    String getDescription();
}