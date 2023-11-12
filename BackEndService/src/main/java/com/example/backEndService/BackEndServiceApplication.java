package com.example.backEndService;

import com.example.backEndService.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EntityScan(basePackages = "com.example.backEndService.entities")
public class BackEndServiceApplication {
    @Autowired
    private CommonService commonService;
    public static void main(String[] args) {
        SpringApplication.run(BackEndServiceApplication.class, args);
    }
    @PostConstruct
    public void initRole(){
        commonService.createBaseRole();
    }
}
