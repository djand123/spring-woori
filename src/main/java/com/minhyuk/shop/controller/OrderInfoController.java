package com.minhyuk.shop.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.minhyuk.shop.domain.OrderInfo;
import com.minhyuk.shop.domain.User;
import com.minhyuk.shop.repository.UserRepository;
import com.minhyuk.shop.service.OrderInfoService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderInfoController {
    private final OrderInfoService orderInfoService;
    private final UserRepository userRepository;

    //주문정보 생성
    @PostMapping("/create")
    public ResponseEntity<Long> createOrder(
        @RequestParam("userId") Long userId,
        @RequestParam("productId") Long productId,
        @RequestParam("name") String name,
        @RequestParam("phoneNumber") String phoneNumber,
        @RequestParam Map<String, String> allParams
    ){
        Map<String, Integer> sizeQuantityMap = new HashMap<>();

        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            if (entry.getKey().startsWith("sizeQuantityMap[")) {
                String size = entry.getKey().replaceAll("sizeQuantityMap\\[|\\]", "");
                Integer quantity = Integer.parseInt(entry.getValue());
                sizeQuantityMap.put(size, quantity);
            }
        }
        Long orderId = orderInfoService.createOrderInfo(
        userId,
        productId,
        name,
        phoneNumber,
        sizeQuantityMap
    );
    return ResponseEntity.ok(orderId);
    }

    //배송 최신순3개 보기
    @GetMapping("/top3")
    public ResponseEntity<List<OrderInfo>> orderInfotop3(Principal principal){
        if(principal != null){
            String email = principal.getName();
            User user = userRepository.findByEmail(email);
            return ResponseEntity.ok(orderInfoService.OrderInfoTop3(user));
        }else{
            return ResponseEntity.ok( null);
        }
    }

    //배송 최신순 8개 보기
    @GetMapping("/top8")
    public ResponseEntity<List<OrderInfo>> orderInfoTop8(Principal principal){
        if(principal != null){
        String email = principal.getName();
        User user = userRepository.findByEmail(email);
        return ResponseEntity.ok(orderInfoService.OrderInfoTop8(user));
        }else{
            return ResponseEntity.ok(null);
        }


    }
    
}
