package com.minhyuk.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhyuk.shop.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

    
    
}
