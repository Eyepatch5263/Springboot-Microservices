package com.eyepatch.works.catalogservice.web.controllers;
import com.eyepatch.works.catalogservice.domain.PagedResult;
import com.eyepatch.works.catalogservice.domain.Product;
import com.eyepatch.works.catalogservice.domain.ProductNotFoundException;
import com.eyepatch.works.catalogservice.domain.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    PagedResult<Product> getProducts(@RequestParam(name="page",defaultValue = "1") int pageNo ){
        return productService.getProducts(pageNo);
    }
    @GetMapping("/{code}")
    ResponseEntity<Product> getProduct(@PathVariable("code") String code){
        return productService.getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(()-> ProductNotFoundException.forCode(code));
    }
}
