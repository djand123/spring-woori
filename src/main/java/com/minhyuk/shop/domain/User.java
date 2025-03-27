package com.minhyuk.shop.domain;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity(name = "User")
@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor // 모든 필드를 받는 생성자
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_User;   //사용자 아이디

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_Role", nullable = false)
    @ToString.Exclude
    private Role role;        //사용자 권한 
    
    @ManyToOne
    @JoinColumn(name = "id_Gender", nullable = false)
    @ToString.Exclude
    private Gender gender;      //사용자 성별
    
    @Column(nullable = false)
    private String name;        //사용자 이름

    @Column(nullable = false)
    private String email;       //사용자 이메일

    @Column(nullable = false)
    private String password;       //사용자 이메일

    
}
