package com.minhyuk.shop.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import com.minhyuk.shop.domain.Image;
import com.minhyuk.shop.domain.Product;
import com.minhyuk.shop.repository.ImageRepository;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

// import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {
    @Value("${file.upload-dir}")
    private String uploadDir;       // yml에서 설정한 경로 

    private final ImageRepository imageRepository;

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

    //이미지 등록하고 저장
    public void addImgs(MultipartFile[] files, Product product) throws IOException{
        //파일 경로
        // String dirPath = "C:/DEV_ENV/VS_CODE/workspace/shop/img/"+product.getId()+"/";

        //상대 경로로 파일 경로 설정
        String dirPath = uploadDir + product.getId() + "/";

        //files의 수만큼 반복
        for(MultipartFile file: files){
            if(file.isEmpty()){
                continue;
            }

            //파일 이름
            String fileName = UUID.randomUUID().toString()+"_"+file.getOriginalFilename();

            //파일 저장
            Path path = Paths.get(dirPath + fileName);
            Files.write(path, file.getBytes());

            //img 객체 생성 및 저장
            Image image = Image.builder()
                                .name(fileName)
                                .dir(dirPath)           // 파일경로 저장
                                .product(product)
                                .build();
             imageRepository.save(image);
        }   
    }

    //이미지 삭제
    public void delImage(Image image){

        String saveDir = new File(uploadDir).getAbsolutePath(); // 상대경로를 절대경로로 변환


        File f = new File(saveDir, image.getName());

        if(f.exists()){
            if (f.delete()) {
                System.out.println("파일 삭제 완료");
            }else{
                System.out.println("파일 삭제 실패");
            }
        }else{
            System.out.println("파일이 존재하지 않습니다.");
        }

    }

    @Transactional
    public void deleteByProduct(Product product){
        List<Image> images = imageRepository.findByProduct(product);
        for(Image image : images){
            File file = new File(image.getDir(), image.getName());
            if(file.exists()){
                file.delete();
            }
        }
        
        imageRepository.deleteByProduct(product);
    }

    

    
}
