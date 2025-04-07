package com.minhyuk.shop.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minhyuk.shop.domain.Role;
import com.minhyuk.shop.domain.User;
import com.minhyuk.shop.repository.GenderRepository;
import com.minhyuk.shop.repository.RoleRepository;
import com.minhyuk.shop.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final RoleRepository roleRepository;
    private final GenderRepository genderRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    //사용자 추가
    @Transactional
    public Long createUser(User user){
        
        //이메일 중복 확인
        if(userRepository.existsByemail(user.getEmail())){
            throw new IllegalArgumentException("이미 존재하는 이메일 입니다");
        }

        //권한 설정
        Long defaultRoleId = 2L;

        Long roleId = 
        (user.getRole() == null || user.getRole().getId() == null) ? defaultRoleId : user.getRole().getId();

        Role role = roleRepository.findById(roleId)
        .orElseThrow(()-> new IllegalArgumentException("해당하는 권한이 없습니다."));

        user.setRole(role);

        //성별 설정
        user.setGender(genderRepository.findById(user.getGender().getId())
        .orElseThrow(()-> new IllegalArgumentException("해당하는 성별이 없습니다")));

        //비밀번호 암호화로 저장
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //저장하기
        user = userRepository.saveAndFlush(user);

        return user.getId();
    }

    //사용자 삭제하기
    @Transactional
    public void deleteUser(User user){
        user = userRepository.findById(user.getId())
        .orElseThrow(()-> new IllegalArgumentException("해당하는 유저 없습니다."));

        userRepository.delete(user);
    }


    
}
