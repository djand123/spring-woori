package com.minhyuk.shop.domain;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Product_Size")
public class ProductSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                 // 상품 사이즈 아이디

    @ManyToOne
    @JoinColumn(name = "id_Product")
    @ToString.Exclude
    private Product product;        // 상품 아이디

    @ManyToOne
    @JoinColumn(name = "id_Size")
    @ToString.Exclude
    private Size size;              // 사이즈 아이디
    
}
