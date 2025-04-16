package com.minhyuk.shop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
// import net.bytebuddy.implementation.bytecode.ByteCodeAppender.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orderproduct")
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 주문상세 아이디
    private Long id; 

    @ManyToOne
    @JoinColumn(name = "id_Product")
    @ToString.Exclude
    private Product product;                // 상품 아이디

    @ManyToOne
    @JoinColumn(name = "id_Order")
    @ToString.Exclude
    private OrderInfo orderinfo;                // 주문

    @ManyToOne
    @JoinColumn(name = "id_Size")
    private Size size;                         // 사이즈

    @Column(nullable = false)
    private int quantity;                       //수량

    @Column(nullable = false)
    private int price;                          //가격

    
}
