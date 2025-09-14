package com.eyepatch.works.catalogservice.domain;
import com.eyepatch.works.catalogservice.ApplicationProperties;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional // it tells that the product service layer will be transactional since this layer deals with DB by making some transactional call like insert,update, delete any query so if it's successfully able to make the db call then only we want the transaction to happen but in case some exception or error occurs we want it to roll back.
public class ProductService {
    final ProductRepository productRepository;
    final ApplicationProperties properties;

    ProductService(ProductRepository productRepository, ApplicationProperties properties) {
        this.productRepository = productRepository;
        this.properties = properties;
    }

    public PagedResult<Product> getProducts(int pageNo){
        pageNo=pageNo<=1?0:pageNo-1;
        Sort sort = Sort.by("name").ascending();
        Pageable pageable = PageRequest.of(pageNo, properties.pageSize(),sort);
        Page<Product> productPage = productRepository.findAll(pageable).map(ProductWrapper::toProduct);
        return new PagedResult<>(
                productPage.getContent(),
                productPage.getTotalElements(),
                productPage.getNumber()+1,
                productPage.getTotalPages(),
                productPage.isFirst(),
                productPage.isLast(),
                productPage.hasNext(),
                productPage.hasPrevious()
        );
    }

    public Optional<Product> getProductByCode(String code){
        return productRepository.findByCode(code).map(ProductWrapper::toProduct);
    }
}
