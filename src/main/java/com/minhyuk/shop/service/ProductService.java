package com.minhyuk.shop.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.minhyuk.shop.domain.Category;
import com.minhyuk.shop.domain.Product;
import com.minhyuk.shop.repository.CategoryRepository;
import com.minhyuk.shop.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    //상품 등록
    // @Transactional
    // public Product create(Product product, MultipartFile[] files, Category category){
        
    // }

    //상품 삭제
    //상품 수정
    //상품 한개 보기
    //상품 전체 보기
    //
    
}
