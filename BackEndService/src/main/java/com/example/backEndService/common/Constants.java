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

    public interface COMMON_CODE {
        String SUCCESS = "SUCCESS";
        Integer SUCCESS_CODE = 200;
    }

    public interface ORDER_CODE {
        Integer PAYMENT_PENDING = 0;
        Integer PAYMENT_SUCCESSFUL = 1;
    }
}
