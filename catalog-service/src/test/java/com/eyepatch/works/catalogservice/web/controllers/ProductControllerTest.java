package com.eyepatch.works.catalogservice.web.controllers;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import com.eyepatch.works.catalogservice.AbstractIT;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test-data.sql")
public class ProductControllerTest extends AbstractIT {
    @Test
    public void  shouldReturnProducts(){
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products")
                .then()
                .statusCode(200)
                .body("data",hasSize(10))
                .body("totalElements",is(15))
                .body("pageNumber",is(1))
                .body("totalPages",is(2))
                .body("isFirst",is(true))
                .body("isLast",is(false))
                .body("hasNext",is(true))
                .body("hasPrevious",is(false));


    }

}