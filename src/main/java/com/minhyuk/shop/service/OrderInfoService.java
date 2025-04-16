package com.minhyuk.shop.service;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.minhyuk.shop.domain.OrderInfo;
import com.minhyuk.shop.domain.OrderProduct;
import com.minhyuk.shop.domain.Product;
import com.minhyuk.shop.domain.Size;
import com.minhyuk.shop.domain.Status;
import com.minhyuk.shop.domain.User;
import com.minhyuk.shop.repository.OrderInfoRepository;
import com.minhyuk.shop.repository.OrderProductRepository;
import com.minhyuk.shop.repository.ProductRepository;
import com.minhyuk.shop.repository.SizeRepository;
import com.minhyuk.shop.repository.StatusRepository;
import com.minhyuk.shop.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderInfoService {
    private final OrderInfoRepository orderInfoRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final SizeRepository sizeRepository;
    private final OrderProductRepository orderProductRepository;
    private final StatusRepository statusRepository;

    //고객배송현황 3개 조회(최신순)
    @Transactional
    public List<OrderInfo> OrderInfoTop3(User user){
        return orderInfoRepository.findTop3ByUserOrderByRegDateDesc(user);
    }

    @Transactional
    //조회시 고객배송현황 8개 조회
    public List<OrderInfo> OrderInfoTop8(User user){
        return orderInfoRepository.findTop8ByUserOrderByRegDateDesc(user);
    } 
    
    
    //주문정보, 주문상세 저장
    @Transactional
    public Long createOrderInfo(
    Long userId, Long productId,
    String name, String phoneNumber,
    Map<String, Integer> sizeQuantityMap){

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다."));

    Product product = productRepository.findById(productId)
        .orElseThrow(()-> new IllegalArgumentException("해당하는 상품이 없습니다"));

    Status status = statusRepository.findById((long) 1)
        .orElseThrow(()-> new IllegalArgumentException("해당하는 상태없음"));

    OrderInfo orderInfo = new OrderInfo();
    orderInfo.setUser(user);
    orderInfo.setName(name);
    orderInfo.setStatus(status);
    orderInfo.setPhoneNumber(phoneNumber);
    orderInfo.setRegDate(LocalDateTime.now());
    orderInfo.setQuantity(0);
    orderInfo.setPrice(0);
    orderInfoRepository.save(orderInfo);

    int totalQuantity = 0;
    int totalPrice = 0;

    for (Map.Entry<String, Integer> entry : sizeQuantityMap.entrySet()) {
        String sizeName = entry.getKey(); // 사이즈 s
        int quantity = entry.getValue(); // 수량 2

        Size size = sizeRepository.findBySize(sizeName)
        .orElseThrow(()-> new IllegalArgumentException("해당하는 사이즈가 없습니다"));


        //총 수량
        totalQuantity += quantity;
        //총 가격
        int sumPrice = quantity * product.getPrice();

        totalPrice += sumPrice;

        //OrderProduct 생성
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setOrderinfo(orderInfo);
        orderProduct.setSize(size);
        orderProduct.setQuantity(quantity);
        orderProduct.setPrice(sumPrice);

        orderProductRepository.save(orderProduct);
    }

    orderInfo.setQuantity(totalQuantity);
    orderInfo.setPrice(totalPrice);
    orderInfoRepository.save(orderInfo);

    return orderInfo.getId();
}

}
