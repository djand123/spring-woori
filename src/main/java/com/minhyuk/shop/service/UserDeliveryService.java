package com.minhyuk.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.minhyuk.shop.domain.User;
import com.minhyuk.shop.domain.UserDelivery;
import com.minhyuk.shop.repository.UserDeliveryRepository;
import com.minhyuk.shop.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDeliveryService {
    private final UserRepository userRepository;
    private final UserDeliveryRepository userDeliveryRepository;

    //고객 배송현황 5개 최신순
    public List<UserDelivery> deliveryTop5(Long userId){
        List<UserDelivery> listTop5 = userDeliveryRepository.findTop5ByUser_IdOrderByRegdateDesc(userId);
        return listTop5;
    }

    // 고객 전체 배송현황 조회
    public List<UserDelivery> deliveryAll(Long userId){
        List<UserDelivery> listAll = userDeliveryRepository.findByUser_IdOrderByRegdateDesc(userId)
        return listAll;
    }
    
}
