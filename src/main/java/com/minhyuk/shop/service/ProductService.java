package com.minhyuk.shop.service;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.minhyuk.shop.domain.Category;
import com.minhyuk.shop.domain.Gender;
import com.minhyuk.shop.domain.Product;
import com.minhyuk.shop.repository.CategoryRepository;
import com.minhyuk.shop.repository.GenderRepository;
import com.minhyuk.shop.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final GenderRepository genderRepository;
    private final ImageService imageService;

    // 상품 등록
    @Transactional
    public long create(Product product, MultipartFile[] files) throws IOException{
        // product.setCategory(categoryRepository.findById(null));

        //카테고리의 아이디를 가져와서 product의 category아이디로 설정
        product.setCategory(categoryRepository.findById(product.getCategory().getId())
        .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다.")));

        //gender의 id를 가져와서 product의 id로 설정
        product.setGender(genderRepository.findById(product.getGender().getId())
        .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다.")));

        imageService.addImgs(files, product);

        product = productRepository.saveAndFlush(product); // 즉시 저장
        return product.getId();
 
    }

    //상품 삭제
    @Transactional
    public String delete(Long productId){
        productRepository.deleteById(productId);
        return "delete OK";
    }

    //상품 수정
    //상품 한개 보기
    //상품 전체 보기
    //
    
}
