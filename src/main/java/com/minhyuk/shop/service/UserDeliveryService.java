package com.minhyuk.shop.service;

import java.beans.Transient;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minhyuk.shop.domain.Delivery;
import com.minhyuk.shop.domain.User;
import com.minhyuk.shop.domain.UserDelivery;
import com.minhyuk.shop.repository.DeliveryRepository;
import com.minhyuk.shop.repository.UserDeliveryRepository;
import com.minhyuk.shop.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDeliveryService {
    private final UserRepository userRepository;
    private final UserDeliveryRepository userDeliveryRepository;
    private final DeliveryRepository deliveryRepository;

    //고객 배송현황 5개 최신순
    @Transactional
    public List<UserDelivery> deliveryTop5(Long userId){
        List<UserDelivery> listTop5 = userDeliveryRepository.findTop5ByUser_IdOrderByRegdateDesc(userId);
        return listTop5;
    }

    // 고객 전체 배송현황 조회
    @Transactional
    public List<UserDelivery> deliveryAll(Long userId){
        List<UserDelivery> listAll = userDeliveryRepository.findByUser_IdOrderByRegdateDesc(userId);
        return listAll;
    }

    //배송지 추가
    @Transient
    public void createAddress(Long userId, String address, String name){

        User user = userRepository.findById(userId)
        .orElseThrow(()->new IllegalArgumentException(" 해당하는 유저가 없습니다"));

        Delivery delivery = new Delivery();
        delivery.setName(name);
        delivery.setType(address);
        deliveryRepository.save(delivery);

        UserDelivery userDelivery = new UserDelivery();
        userDelivery.setUser(user);
        userDelivery.setDelivery(delivery);
        userDeliveryRepository.save(userDelivery);

    }



    // 배송지 삭제
    @Transactional
    public void deleteAddress(Long userId, Long userDeliverId){

        // 해당 Id가 있는지 확인
        UserDelivery userDelivery = userDeliveryRepository.findById(userDeliverId)
        .orElseThrow(()->new IllegalArgumentException("해당하는 주소가 없습니다"));

        if(!userDelivery.getUser().getId().equals(userId)){
            throw new IllegalArgumentException("해당 유저의 배송지가 아닙니다");
        }

        // 삭제할 Delivery 정보 가져오기
        Delivery delivery = userDelivery.getDelivery();

        //userDelivery에서 해당하는 레코드 삭제
        userDeliveryRepository.delete(userDelivery);

        //delivery에서도 해당하는 레코드 삭제
        deliveryRepository.delete(delivery);
    }


    
}
