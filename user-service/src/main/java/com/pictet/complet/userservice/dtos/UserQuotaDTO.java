package com.pictet.complet.userservice.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Builder;

import java.io.Serializable;
import java.util.UUID;

@Builder
public record UserQuotaDTO(UUID userId, long quota) implements Serializable {

}
