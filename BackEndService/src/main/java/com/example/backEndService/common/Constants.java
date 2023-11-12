package com.example.backEndService.common;

public final class Constants {
    public interface ROLE {
        String ADMIN = "ADMIN";
        String CUSTOMER = "CUSTOMER";
    }
    public interface TOKEN {
        Long LIFE_SPAN = 696969L;

        String SECRET = "segggggg";
    }

    public interface CUSTOMER_STATUS {
        Integer INACTIVE = 0;
        Integer ACTIVE = 1;
    }
}
