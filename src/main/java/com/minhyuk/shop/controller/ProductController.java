package com.minhyuk.shop.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minhyuk.shop.domain.Gender;
import com.minhyuk.shop.domain.Product;
import com.minhyuk.shop.domain.User;
import com.minhyuk.shop.repository.UserRepository;
import com.minhyuk.shop.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class ProductController {
    private final ProductService productService;
    private final UserRepository userRepository;

    // 최근에 등록된 상품 3개
    @GetMapping("/product/top6regDate")
    public ResponseEntity<List<Product>> getRecentProducts(Principal principal){
        if(principal != null){
            String email = principal.getName();
            User user = userRepository.findByEmail(email);
            return ResponseEntity.ok(productService.find6ProductregdateByGender(user.getGender()));
        }else{
            return ResponseEntity.ok(productService.find6Productregdate());
        }
    }

    //카테고리별 상품 리스트
    // @GetM
    

    
}
