package com.example.backEndService.sercurity;

import com.example.backEndService.common.Constants;
import com.example.backEndService.dto.TokenInfo;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtTokenService {
    public String genToken(TokenInfo info){
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + Constants.TOKEN.LIFE_SPAN);
        Map<String, Object> claims = new HashMap<>();

        claims.put("userId", info.getUserId());
        claims.put("username", info.getUsername());
        claims.put("roles", info.getRoles());
        claims.put("customerId", info.getCustomerId());
        claims.put("email", info.getEmail());
        claims.put("mobile", info.getMobile());
        claims.put("cardId", info.getCardId());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, Constants.TOKEN.SECRET)
                .compact();
    }

    public TokenInfo readToken(String token) {
        Claims claims = Jwts.parser()
                    .setSigningKey(Constants.TOKEN.SECRET)
                    .parseClaimsJws(token)
                    .getBody();

            TokenInfo tokenInfo = new TokenInfo();
            tokenInfo.setUserId(claims.get("userId",Long.class));
            tokenInfo.setUsername(claims.get("username",String.class));
            tokenInfo.setRoles(claims.get("roles",List.class));
            tokenInfo.setEmail(claims.get("email",String.class));
            tokenInfo.setMobile(claims.get("mobile",String.class));
            tokenInfo.setCustomerId(claims.get("customerId",Long.class));
            tokenInfo.setCardId(claims.get("cardId",Long.class));

        return tokenInfo;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(Constants.TOKEN.SECRET).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty.");
        }
        return false;
    }

}
