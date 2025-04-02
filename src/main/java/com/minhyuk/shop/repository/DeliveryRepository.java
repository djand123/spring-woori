package com.minhyuk.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhyuk.shop.domain.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>{
    
}
