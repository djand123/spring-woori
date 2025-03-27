package com.minhyuk.shop.domain;

import java.time.LocalDateTime;


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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                             // 상품 id

    @ManyToOne
    @JoinColumn(name = "id_Gender")
    @ToString.Exclude
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "id_Category")
    @ToString.Exclude
    private Category category;

    @Column(nullable = false)
    private String name;                        // 상품이름
    
    @Column(nullable = false)
    private int price;                          // 상품가격

    @Column(name = "des", nullable = false)
    private String info;                         // 상품설명

    @Column(nullable = false)
    private int quantity;                       // 상품수량

    @Column(nullable = false)
    private LocalDateTime created_At;           // 상품등록시간





    


    
}
