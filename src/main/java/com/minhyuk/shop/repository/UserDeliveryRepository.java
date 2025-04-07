package com.minhyuk.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhyuk.shop.domain.User;
import com.minhyuk.shop.domain.UserDelivery;

public interface UserDeliveryRepository extends JpaRepository<UserDelivery,Long>{

    //배송지 ID로 삭제하기
    void deleteByDelivery_Id(Long deliveryId);

    //사용자 배송지 3개 조회하기
    List<UserDelivery> findTop3ByUser(User user);

    //사용자의 전체 배송지 조회하기
    List<UserDelivery> findByUser(User user);



}
