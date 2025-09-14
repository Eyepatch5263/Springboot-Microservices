package com.eyepatch.works.catalogservice.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest(
        properties = {
                "spring.test.database.replace=true",
                "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db"
        }
)

@Sql("/test-data.sql")
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldGetAllProducts() {
        List<ProductEntity> productList = productRepository.findAll();
        assertThat(productList).hasSize(15);
    }
}
