package com.awbd.book;

import com.awbd.book.model.Discount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "discount-service" /*, url = "localhost:8082"*/)
@RibbonClient(name = "discount-service")
public interface DiscountServiceProxy {
    @GetMapping("/discount")
    Discount findDiscount();
}
