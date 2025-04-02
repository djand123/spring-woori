package com.minhyuk.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhyuk.shop.domain.Product;
import com.minhyuk.shop.domain.ProductSize;

public interface ProductSizeRepository extends JpaRepository<ProductSize, Long>{

    void deleteByProduct(Product product);
    
}
