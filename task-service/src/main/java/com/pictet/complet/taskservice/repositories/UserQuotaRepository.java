package com.pictet.complet.taskservice.repositories;

import com.pictet.complet.taskservice.entities.QuotaUser;
import com.pictet.complet.taskservice.models.UserQuotaSnapshotDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserQuotaRepository extends JpaRepository<QuotaUser, UUID> {
    // List<QuotaUser> saveAll(List<UserQuotaSnapshotDTO> userQuotaSnapshotDTOList);

}
