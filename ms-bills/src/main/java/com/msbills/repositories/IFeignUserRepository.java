package com.msbills.repositories;


import com.msbills.configuration.feign.FeignInterceptor;
import com.msbills.configuration.feign.OAuthFeignConfig;
import com.msbills.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="user-service", url = "http://localhost:8084/", configuration = FeignInterceptor.class)
public interface IFeignUserRepository {

    @GetMapping("/users/findUsername/{username}")
    User findByUsername(@PathVariable("username") String username);

}
