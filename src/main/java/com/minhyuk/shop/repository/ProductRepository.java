package com.minhyuk.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhyuk.shop.domain.Category;
import com.minhyuk.shop.domain.Gender;
import com.minhyuk.shop.domain.Product;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long> {

    // 최신 등록 상품 (성별)
    List<Product> findTop6ByGenderOrderByRegDateDesc(Gender gender);

    // 최신 등록 상품 (전체)
    List<Product> findTop6ByOrderByRegDateDesc(); // ← 수정!

    // 카테고리별 상품 나열 (성별)
    List<Product> findByCategoryAndGenderOrderByRegDateDesc(Category category, Gender gender);

    // 카테고리별 상품 나열 (전체)
    List<Product> findByCategoryOrderByRegDateDesc(Category category);

    // 전체 상품 나열 (성별)
    List<Product> findByGender(Gender gender);




}

