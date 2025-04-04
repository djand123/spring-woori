package com.minhyuk.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhyuk.shop.domain.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long>{
    
}
