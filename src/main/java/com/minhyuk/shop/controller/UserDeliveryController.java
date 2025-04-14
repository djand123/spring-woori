package com.minhyuk.shop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.minhyuk.shop.domain.Delivery;
import com.minhyuk.shop.domain.UserDelivery;
import com.minhyuk.shop.service.UserDeliveryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/delivery")
public class UserDeliveryController {
    private final UserDeliveryService userDeliveryService;
    
    //배송지 전체 조회하기
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<UserDelivery>> showUserDelivery(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(userDeliveryService.showUserDelivery(userId));
    }

    //배송지 추가하기
    @PostMapping("/users/{userId}")
    public ResponseEntity<?> create(@RequestParam("address") String address,
                                    @RequestParam("name") String name,
                                    @PathVariable("userId") Long userId){

        UserDelivery createUserDelivery = userDeliveryService.createAddress(userId, address, name);
        return new ResponseEntity<>(createUserDelivery, HttpStatus.CREATED);
 }

    //배송지 삭제하기
    @DeleteMapping("/delete/{userId}/{userDeliveryId}")
    public ResponseEntity<?> delete(@PathVariable Long userId, @PathVariable Long userDeliveryId){
        return new ResponseEntity<>(userDeliveryService.deleteAddress(userId, userDeliveryId), HttpStatus.OK);
    }

}
