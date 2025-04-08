package com.minhyuk.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhyuk.shop.domain.OrderInfo;
import com.minhyuk.shop.domain.User;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long>{
    
    // 8개의 주문정보 조회
    List<OrderInfo> findTop8ByUserOrderByRegDateDesc(User user);

    //고객배송현황 3개 조회
    List<OrderInfo> findTop3ByUserOrderByRegDateDesc(User user);

}
