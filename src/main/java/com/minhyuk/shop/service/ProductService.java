package com.minhyuk.shop.service;
import java.io.IOException;
import java.security.PublicKey;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.minhyuk.shop.domain.Category;
import com.minhyuk.shop.domain.Gender;
import com.minhyuk.shop.domain.Product;
import com.minhyuk.shop.domain.ProductSize;
import com.minhyuk.shop.domain.Size;
import com.minhyuk.shop.repository.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final GenderRepository genderRepository;
    private final ImageService imageService;
    private final SizeRepository sizeRepository;
    private final ProductSizeRepository productSizeRepository;

    // 상품 등록
    @Transactional
    public long create(Product product, MultipartFile[] files, List<Long> sizeIds) throws IOException{

        //카테고리의 아이디를 가져와서 product의 category아이디로 설정
        product.setCategory(categoryRepository.findById(product.getCategory().getId())
        .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다.")));

        //gender의 id를 가져와서 product의 id로 설정
        product.setGender(genderRepository.findById(product.getGender().getId())
        .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다.")));

        product = productRepository.saveAndFlush(product); // Product 즉시 저장

        imageService.addImgs(files, product); // 이미지 저장

        //사이즈를 중간테이블에 저장
        for(Long sizeId :sizeIds ){
            Size size = sizeRepository.findById(sizeId)
            .orElseThrow(()-> new IllegalArgumentException("해당 사이즈가 없습니다"));

            ProductSize productSize = new ProductSize();
            productSize.setProduct(product);
            productSize.setSize(size);

            productSizeRepository.save(productSize);
        }
        return product.getId();
    }

    //상품 삭제
    @Transactional
    public void delete(Long productId){
        productRepository.deleteById(productId);
    }

    // 상품 수정
    @Transactional
    public long update(Long productId, Product updateProduct,MultipartFile[] files, List<Long> sizeIds) throws IOException{
        
        // 존재하는 상품인지 확인 
        Product product = productRepository.findById(productId)
        .orElseThrow(()-> new IllegalArgumentException("해당하는 상품이 없다"));

        //상품정보 업데이트
        product.setName(updateProduct.getName());
        product.setPrice(updateProduct.getPrice());
        product.setInfo(updateProduct.getInfo());
        product.setQuantity(updateProduct.getQuantity());
 
        // 카테고리 유효성 검증과 설정
        if(updateProduct.getCategory() != null){
            product.setCategory(categoryRepository.findById(updateProduct.getCategory().getId())
            .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다.")));
        }

        //성별 유효성 검사와 업데이트
        if(updateProduct.getGender() != null){
            product.setGender(genderRepository.findById(updateProduct.getGender().getId())
            .orElseThrow(()-> new IllegalArgumentException("해당하는 성별이 없습니다")));
        }

        //사이즈 정보 업데이트
        //해당하는 상품의 ProductSize를 전부 삭제하고 새로 등록
        productSizeRepository.deleteByProduct(product);

        for(Long sizeId : sizeIds){
            Size size = sizeRepository.findById(sizeId)
            .orElseThrow(()->new IllegalArgumentException("해당하는 사이즈가 없음"));
            ProductSize productSize = new ProductSize();
            productSize.setProduct(product);
            productSize.setSize(size);
            productSizeRepository.save(productSize);
        }
        
        //이미지 업데이트
        if(files != null && files.length > 0){
            imageService.deleteByProduct(product);
            imageService.addImgs(files, product);
        }

        //저장하고 product의 아이디 값을 리턴 
        product = productRepository.saveAndFlush(product);
        return product.getId();

    }

    //상품 한개 보기
    //카테고리별 상품 보기(성별)
    @Transactional
    public List<Product> findProductByCategory (Category category, Gender gender){
        List<Product> products = productRepository.findByCategoryAndGenderOrderByRegdateDesc(category, gender);
        return products;
    }

    //전체 상품보기 (성별)
    @Transactional
    public List<Product> findAllProduct (Gender gender){
        List<Product> products = productRepository.findByGender(gender);
        return products;
    }

    //최신 상품보기(성별)
    @Transactional
    public List<Product> find6Productregdate(Gender gender){
        List<Product> products = productRepository.findTop6ByGenderOrderByRegdateDesc(gender);
        return products;
    }

    //상품 한개 선택하기
    @Transactional
    public Product findproductById(Long productId){
        Product product = productRepository.findById(productId)
        .orElseThrow(()-> new IllegalArgumentException("해당하는 상품이 없습니다"));
        return product;
    }

    


}
