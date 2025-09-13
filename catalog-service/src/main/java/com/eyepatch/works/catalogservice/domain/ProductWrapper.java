package com.eyepatch.works.catalogservice.domain;

class ProductWrapper {
    static Product toProduct(ProductEntity productEntity){
        return new Product(
                productEntity.getCode(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getImageUrl(),
                productEntity.getPrice()
        );
    }
}
