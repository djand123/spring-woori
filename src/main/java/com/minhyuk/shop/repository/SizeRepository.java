package com.minhyuk.shop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhyuk.shop.domain.Size;

public interface SizeRepository extends JpaRepository<Size, Long>{

    Optional<Size> findBySize(String size);
    
    
}
