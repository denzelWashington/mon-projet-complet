package com.pictet.complet.taskservice.services;

import com.pictet.complet.taskservice.entities.QuotaUser;
import com.pictet.complet.taskservice.repositories.UserQuotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQuotaService {

    private final UserQuotaRepository userQuotaRepository;

    UserQuotaService(UserQuotaRepository userQuotaRepository) {
        this.userQuotaRepository = userQuotaRepository;
    }

    public List<QuotaUser> saveAllQuota(List<QuotaUser> userQuotaSnapshotDTOList) {
        return this.userQuotaRepository.saveAll(userQuotaSnapshotDTOList);
    }

    public long getCountQuotas() {
        return this.userQuotaRepository.count();
    }
}
