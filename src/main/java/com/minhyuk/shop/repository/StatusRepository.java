package com.minhyuk.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhyuk.shop.domain.Status;

public interface StatusRepository extends JpaRepository<Status, Long>{

    //아이디로 조회하기
    String findStatusById(Long id);
}
