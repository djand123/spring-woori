package com.minhyuk.shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhyuk.shop.domain.OrderInfo;
import com.minhyuk.shop.domain.User;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long>{
    
    // 6개의 주문정보 조회
    List<OrderInfo> findTop6ByUserOrderByRegdateDesc(User user);

}
