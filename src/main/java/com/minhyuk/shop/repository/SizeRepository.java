package com.minhyuk.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhyuk.shop.domain.Size;

public interface SizeRepository extends JpaRepository<Size, Long>{
    
}
