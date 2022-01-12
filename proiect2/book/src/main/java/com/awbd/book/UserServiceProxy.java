package com.awbd.book;

import com.awbd.book.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "user-service" /*, url = "localhost:8082"*/)
@RibbonClient(name = "user-service")
public interface UserServiceProxy {
    @GetMapping("/user")
    User findUser();
}
