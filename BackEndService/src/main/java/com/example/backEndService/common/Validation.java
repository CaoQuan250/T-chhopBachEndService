package com.example.backEndService.common;

import java.time.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validation {
    public static boolean emailValid(String email){
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    public static boolean mobileValid(String mobile){
        String regex = "\\d{10}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }

    public static boolean ageVerify(Date birth){
        LocalDate dateOfBirth = birth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        LocalDate currentDateTime = LocalDate.now();

        Period period = Period.between(dateOfBirth, currentDateTime);

        return period.getYears() > 16;
    }
}