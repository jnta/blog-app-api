package com.jonata.blog.repositories;

import com.jonata.blog.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    
}
