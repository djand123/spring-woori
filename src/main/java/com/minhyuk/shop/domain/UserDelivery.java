package com.minhyuk.shop.domain;

import java.time.LocalDateTime;

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
    @Column(name = "id_user_delivery") 
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_User")
    @ToString.Exclude
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_Delivery")
    @ToString.Exclude
    private Delivery delivery;

    @Column(nullable = false)
    private LocalDateTime created;

    @PrePersist
    public void prePersist(){
        this.created = LocalDateTime.now();
    }
    
}
