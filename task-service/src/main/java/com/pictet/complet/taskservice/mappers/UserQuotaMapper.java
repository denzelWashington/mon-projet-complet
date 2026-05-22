package com.pictet.complet.taskservice.mappers;

import com.pictet.complet.taskservice.entities.QuotaUser;
import com.pictet.complet.taskservice.models.UserQuotaSnapshotDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserQuotaMapper {
    QuotaUser toEntity(UserQuotaSnapshotDTO userQuotaSnapshotDTO);
    UserQuotaSnapshotDTO toDTO(QuotaUser quotaUser);
}
