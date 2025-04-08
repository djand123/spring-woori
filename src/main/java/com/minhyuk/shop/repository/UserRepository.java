package com.minhyuk.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.minhyuk.shop.domain.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long>{

    //이메일 중복체크
    Boolean existsByemail(String email);

    //이메일로 찾기
    User findByEmail(String email);

    


    
}
