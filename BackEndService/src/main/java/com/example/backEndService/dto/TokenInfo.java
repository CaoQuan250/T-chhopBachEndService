package com.example.backEndService.dto;

import com.example.backEndService.entities.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenInfo {
    private Long userId;
    private String username;
    private List<Roles> roles;
    private Long customerId;
    private String email;
    private String mobile;
    private Long cardId;
}
