package com.minhyuk.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.minhyuk.shop.domain.Image;
import com.minhyuk.shop.domain.Product;
import com.minhyuk.shop.repository.ImageRepository;
import org.springframework.transaction.annotation.Transactional;
// import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {
    private ImageRepository imageRepository;

    //이미지 보기

    //첨부이미지 저장
    @Transactional
    public Image create(Image image){
        return imageRepository.save(image);
    }

    //상품 아이디로 이미지 검색하기
    @Transactional(readOnly = true)
    public List<Image> findByProduct(Product product){
        return imageRepository.findByProduct(product);
    }

    //이미지 삭제
    @Transactional
    public String delete(Long Id){
        imageRepository.deleteById(Id);
        return "ok";
    }

    //이미지 수정

    

    
}
