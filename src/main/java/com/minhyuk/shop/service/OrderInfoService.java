package com.minhyuk.shop.service;

import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.minhyuk.shop.domain.OrderInfo;
import com.minhyuk.shop.domain.Product;
import com.minhyuk.shop.domain.Size;
import com.minhyuk.shop.domain.User;
import com.minhyuk.shop.repository.OrderInfoRepository;
import com.minhyuk.shop.repository.ProductRepository;
import com.minhyuk.shop.repository.SizeRepository;
import com.minhyuk.shop.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderInfoService {
    private final OrderInfoRepository orderInfoRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final SizeRepository sizeRepository;

    private int getPriceByProduct(Product product){
        return product.getPrice();
    }
    
    @Transient
    public void createOrderInfo(
    Long userId, Long productId,
    String name, String phoneNumber,
    Map<String, Integer> sizeQuantityMap){

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 없습니다."));

    Product product = productRepository.findById(productId)
        .orElseThrow(()-> new IllegalArgumentException("해당하는 상품이 없습니다"));

    OrderInfo orderInfo = new OrderInfo();
    orderInfo.setUser(user);
    orderInfo.setName(name);
    orderInfo.setPhoneNumber(phoneNumber);
    orderInfo.setRegDate(LocalDateTime.now());

    int totalQuantity = 0;
    int totalPrice = 0;

    for (Map.Entry<String, Integer> entry : sizeQuantityMap.entrySet()) {
        String sizeName = entry.getKey(); // 사이즈 s
        int quantity = entry.getValue(); // 수량 2

        Size size = sizeRepository.findBySize(sizeName)
        .orElseThrow(()-> new IllegalArgumentException("해당하는 사이즈가 없습니다"));


        totalQuantity += quantity;
        // totalPrice += quantity * price;
    }

    orderInfo.setQuantity(totalQuantity);
    orderInfo.setPrice(totalPrice);

    orderInfoRepository.save(orderInfo);
}

}
