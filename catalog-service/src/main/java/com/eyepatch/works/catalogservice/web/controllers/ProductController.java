package com.eyepatch.works.catalogservice.web.controllers;
import com.eyepatch.works.catalogservice.domain.PagedResult;
import com.eyepatch.works.catalogservice.domain.Product;
import com.eyepatch.works.catalogservice.domain.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    PagedResult<Product> getProducts(@RequestParam(name="page",defaultValue = "1") int pageNo ){
        return productService.getProducts(pageNo);
    }
}
