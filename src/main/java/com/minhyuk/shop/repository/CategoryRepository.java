package com.minhyuk.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhyuk.shop.domain.Category;
import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, Long>{

    //선택한 아이디로 카테고리 가져오기
    String findKindById(Long id);

    //전체 리스트 가져오기
    List<Category> findAll();
    
}
