package com.pictet.complet.userservice.repositories;

import com.pictet.complet.userservice.dtos.UserDTO;
import com.pictet.complet.userservice.dtos.UserQuotaDTO;
import com.pictet.complet.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("""
            SELECT new com.pictet.complet.userservice.dtos.UserQuotaDTO(u.id, u.quota)
            FROM User u
            """)
    public List<UserQuotaDTO> findAllQuota();
}
