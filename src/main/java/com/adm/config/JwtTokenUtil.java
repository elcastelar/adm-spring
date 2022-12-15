package com.adm.config;

import com.adm.entities.User;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenUtil {

    public static final String TOKEN_APIKEY = "apiKey";

    private static final long TOKEN_EXPIRATION = 24 * 60;

    private final String ISSUER = "ADM-WEB";

    private final String AUDIENCE = "REST-API";


    public static final String secretKey = "4pE8z3PBoHjnV1AhvGk+e8h2p+ShZpOnpr8cwHmMh1w=";

    public String generateAccessToken(User user) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24);

        Date now = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        String tokenGenerated = Jwts.builder()
                .setId(UUID.randomUUID().toString().replaceAll("-", ""))
                .setIssuer(ISSUER)
                .setSubject(user.getUsername())
                .setAudience(AUDIENCE)
                .setIssuedAt(now)
                .setNotBefore(now)
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.decode(secretKey))
                .compact();

        return tokenGenerated;
    }

    public void checkAccess(String apiToken) {
        Jwt parse = Jwts.parser().parse(apiToken);
    }

}
