package com.minhyuk.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhyuk.shop.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    
}
