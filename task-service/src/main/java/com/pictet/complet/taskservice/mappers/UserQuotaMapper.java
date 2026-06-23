package com.pictet.complet.taskservice.mappers;

import com.pictet.complet.taskservice.entities.QuotaUser;
import com.pictet.complet.taskservice.models.UserQuotaSnapshotDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserQuotaMapper {
    //@Mapping(source = "id", target = "userId")
    QuotaUser toEntity(UserQuotaSnapshotDTO userQuotaSnapshotDTO);
    UserQuotaSnapshotDTO toDTO(QuotaUser quotaUser);
}
