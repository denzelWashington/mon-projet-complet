package com.pictet.complet.taskservice.services;

import com.pictet.complet.taskservice.entities.QuotaUser;
import com.pictet.complet.taskservice.repositories.UserQuotaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

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

    public QuotaUser getQuota(UUID userId) {
        return this.userQuotaRepository.getReferenceById(userId);
    }

    @Transactional
    public void decrementQuota(UUID userId) {
        QuotaUser userQuota = getQuota(userId);
        if(userQuota == null) {
            throw new RuntimeException("User not found");
        }

        if (userQuota.getQuota() <= 0) {
            throw new RuntimeException("Quota dépassé !");
        }

        userQuota.setQuota(userQuota.getQuota() - 1);
         this.userQuotaRepository.save(userQuota);
    }
}
