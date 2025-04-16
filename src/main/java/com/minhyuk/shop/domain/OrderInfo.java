package com.minhyuk.shop.domain;

import java.time.LocalDateTime;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orderinfo")
public class OrderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Order")
    private Long id;                     // 주문 아이디

    @ManyToOne
    @JoinColumn(name = "id_User")
    @ToString.Exclude
    private User user;                  // 사용자 아이디

    @ManyToOne
    @JoinColumn(name = "id_Status")
    @ToString.Exclude
    private Status status;                    // 주문상태 아이디

    @Column(name = "total_quantity", nullable = false)
    private int quantity;                       // 주문수량

    @Column(name = "total_price", nullable = false)
    private int price;                      // 주문가격

    @Column(name = "created_At",nullable = false)
    private LocalDateTime regDate;              //생성날짜

    @Column(nullable = false)
    private String name;                        //주문자이름

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;                 //주문자 핸드폰 번호 





    
}
