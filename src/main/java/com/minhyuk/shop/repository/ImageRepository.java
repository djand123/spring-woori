package com.minhyuk.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhyuk.shop.domain.Image;
import com.minhyuk.shop.domain.Product;

public interface ImageRepository extends JpaRepository <Image, Long>{
    
    //제품으로 이미지 찾기
    List<Image> findByProduct(Product product);
    
}
