package com.practice.userservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "email-service",url = "${email.service.url}:${email.service.port}",fallback = EmailServiceClient.EmailServiceFallback.class)
public interface EmailServiceClient {

    @GetMapping(value = "/email/{userId}")
    String buildEmail(@PathVariable(value = "userId") String userId);

    @Component
    class EmailServiceFallback implements EmailServiceClient{

        @Override
        public String buildEmail(String userId) {
            return  userId + "@error.com";
        }
    }

}
