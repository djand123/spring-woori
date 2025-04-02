package com.minhyuk.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhyuk.shop.domain.Category;
import com.minhyuk.shop.domain.Gender;
import com.minhyuk.shop.domain.Product;
import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Long>{

    //최신 등록 상품 (성별)
    List<Product> findTop6ByGenderOrderByRegdateDesc(Gender gender);

    //카테고리별 상품 나열 (성별)
    List<Product> findByCategoryAndGenderOrderByRegdateDesc(Category category, Gender gender);

    //전체 상품 나열(성별)
    List<Product> findByGender(Gender gender);

    //상품 아이디로 상품 찾기
    Product findByid(Long id);

}
