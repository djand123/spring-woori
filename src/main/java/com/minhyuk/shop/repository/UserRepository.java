package com.minhyuk.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.minhyuk.shop.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

    //이메일 중복체크
    Boolean existsByemail(String email);

    


    
}
