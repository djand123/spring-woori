package com.minhyuk.shop.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.minhyuk.shop.domain.Category;
import com.minhyuk.shop.domain.Gender;
import com.minhyuk.shop.domain.Product;
import com.minhyuk.shop.domain.User;
import com.minhyuk.shop.repository.CategoryRepository;
import com.minhyuk.shop.repository.UserRepository;
import com.minhyuk.shop.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    // 최근에 등록된 상품 3개
    @GetMapping("/top6regDate")
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
    @GetMapping("/category/{categoryId}")
    public ResponseEntity <List<Product>> getProductsByCategory(@PathVariable Long categoryId, Principal principal){
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new IllegalArgumentException("해당하는 카테고리 없음"));
        
        if(principal != null){
        String email = principal.getName();
        User user = userRepository.findByEmail(email);
            return ResponseEntity.ok(productService.findProductByCategoryAndGender(category, user.getGender()));
        }else{
            return ResponseEntity.ok(productService.findProductByCategory(category));
        }
    }

    //상품 한개 선택해서 조회
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductinfo(@PathVariable Long productId){
        return ResponseEntity.ok(productService.findproductById(productId));
    }

    //상품 등록하기
    @PostMapping("/create")
    public ResponseEntity<Long> createProduct(
        @RequestPart("product") Product product,
        @RequestPart("files") MultipartFile[] files,
        @RequestParam("sizeIds") List<Long> sizeIds) throws IOException {
            
            Long createdProductId = productService.create(product, files, sizeIds);
            return ResponseEntity.ok(createdProductId);
    }

    //상품 삭제하기

    //상품 수정하기
    

    
}
