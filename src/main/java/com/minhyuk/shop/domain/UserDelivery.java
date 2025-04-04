package com.minhyuk.shop.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "User_Delivery")
public class UserDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_User")
    @ToString.Exclude
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_Delivery")
    @ToString.Exclude
    private Delivery delivery;
    
}
