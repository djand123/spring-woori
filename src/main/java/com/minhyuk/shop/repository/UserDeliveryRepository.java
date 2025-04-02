package com.minhyuk.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhyuk.shop.domain.UserDelivery;

public interface UserDeliveryRepository extends JpaRepository<UserDelivery,Long>{

    //사용자 Id로 배송정보5개  최신순으로 가져오기
    List<UserDelivery> findTop5ByUser_IdOrderByRegdateDesc(Long userId); 


    //사용자Id로 전체 배송정보 가져오기
    List<UserDelivery> findByUser_IdOrderByRegdateDesc(Long userId);

    
}
