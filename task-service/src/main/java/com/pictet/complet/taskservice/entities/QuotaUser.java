package com.pictet.complet.taskservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class QuotaUser {

    @Id
    public UUID userId;

    public long quota;
}
