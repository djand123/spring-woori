package com.minhyuk.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhyuk.shop.domain.Gender;

public interface GenderRepository extends JpaRepository<Gender, Long>{
    
    //아이디로 성별종류 찾기
    String findKindById(Long id);

    //전체 조회하기
    List<Gender> findAll();
}
