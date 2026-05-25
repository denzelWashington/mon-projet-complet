package com.pictet.complet.taskservice.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pictet.complet.taskservice.entities.QuotaUser;
import com.pictet.complet.taskservice.mappers.UserQuotaMapper;
import com.pictet.complet.taskservice.models.UserQuotaSnapshotDTO;
import com.pictet.complet.taskservice.services.UserQuotaService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Component
public class UserServiceClient implements CommandLineRunner {

    final private UserQuotaService userQuotaService;
    final private UserQuotaMapper userQuotaMapper;

    UserServiceClient(UserQuotaService userQuotaService, UserQuotaMapper userQuotaMapper) {
        this.userQuotaService = userQuotaService;
        this.userQuotaMapper = userQuotaMapper;
    }
    @Retry(name = "userService")
    @CircuitBreaker(name = "userService")
    @Override
    public void run(String... args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8082/api/users/quotas"))
                .build();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();

        List<UserQuotaSnapshotDTO> quotas =
                mapper.readValue(response.body(),
                        new TypeReference<List<UserQuotaSnapshotDTO>>() {});

        List<QuotaUser> quotaUserList = quotas.stream().map(this.userQuotaMapper::toEntity).toList();
       if (this.userQuotaService.getCountQuotas() == 0 ) {
           System.out.println("*** JE FAIS LA MISE A JOUR **** :  "+this.userQuotaService.getCountQuotas());
           this.userQuotaService.saveAllQuota(quotaUserList);
       }else {
           System.out.println("*** R. A .S **** :");
       }
    }
}
