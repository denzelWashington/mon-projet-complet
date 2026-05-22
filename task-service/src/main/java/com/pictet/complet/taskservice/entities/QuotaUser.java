package com.pictet.complet.taskservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class QuotaUser {

    @Id
    public UUID id;
    public long quota;
}
