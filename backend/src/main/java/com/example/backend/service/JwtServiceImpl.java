package com.example.backend.service;

import io.jsonwebtoken.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.HashMap;
import java.util.Map;

@Service("JwtService")
public class JwtServiceImpl implements JwtService {

    private String secretKey = "jhqrhgate;qpej!!!@##$pop13m4ntklnaghg546!@#$kadflsa1";
    @Override
    public String getToken(String key, Object value) {
        Date expTime = new Date();
        expTime.setTime(expTime.getTime()+1000*60*30);
        byte[] secretBytekey = secretKey.getBytes();
        Key signKey = new SecretKeySpec(secretBytekey, SignatureAlgorithm.HS256.getJcaName());

        Map<String, Object> headerMAp = new HashMap<>();
        headerMAp.put("typ",  "JWT");
        headerMAp.put("alg", "HS256");

        Map<String, Object> map = new HashMap<>();
        map.put(key, value);

        JwtBuilder builder = Jwts.builder().setHeader(headerMAp)
                .setClaims(map)
                .setExpiration(expTime)
                .signWith(signKey, SignatureAlgorithm.HS256);

        return builder.compact();
    }

    @Override
    public Claims getClaims(String token) {
        if(token != null && !"".equals(token)){
            try{
                byte[] secretBytekey = secretKey.getBytes();
                Key signKey = new SecretKeySpec(secretBytekey, SignatureAlgorithm.HS256.getJcaName());
                return Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token).getBody();
            }
            catch(ExpiredJwtException e){
                //만료됨
            }
            catch(JwtException e){
                //유효하지 않음
            }
        }
        return null;
    }

    @Override
    public boolean isValid(String token) {
        return this.getClaims(token) != null;
    }

    @Override
    public int getId(String token) {
        Claims claims = this.getClaims(token);

        if(claims != null){
            return Integer.parseInt(claims.get("id").toString());
        }

        return 0;
    }
}
