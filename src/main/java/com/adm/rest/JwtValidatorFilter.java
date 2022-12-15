package com.adm.rest;

import com.adm.config.JwtTokenUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.impl.DefaultClaims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtValidatorFilter extends OncePerRequestFilter {

    /**
     * Spring security uses the JSESSION ID cookie
     */
    private final Logger log = LoggerFactory.getLogger(JwtValidatorFilter.class);

    private JwtTokenUtil jwtToken;

    private String createJsonMessage(Map<String, String> map) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            log.error("Error converting Map to Json", e);
        }

        return "";
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        char[] buffer = new char[1000];
        CustomRequestBodyWrapper wrappedRequest = new CustomRequestBodyWrapper(request);

        String msgError = "";

        String restApiPathRegex = wrappedRequest.getContextPath() + "/restapi/.+";
        Map<String, String> mapReponse = new HashMap<>();

        if (!wrappedRequest.getRequestURI().endsWith("login") &&
                wrappedRequest.getRequestURI().matches(restApiPathRegex)) {
            MapType mapType = TypeFactory.defaultInstance().constructMapType(HashMap.class, String.class, String.class);
            Map<String, String> map;

            try {
                map = new ObjectMapper().readValue(wrappedRequest.getReader(), mapType);
            } catch (JsonProcessingException e) {
                logger.warn(String.format("Request by %s with Token API not present", wrappedRequest.getRemoteAddr()));
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(createJsonMessage(Map.of("error", "Missing API Token attribute")));
                return;
            }

            String apiKey = map.get(JwtTokenUtil.TOKEN_APIKEY);
            try {
                DefaultClaims body = (DefaultClaims) Jwts.parser().setSigningKey(JwtTokenUtil.secretKey).parse(apiKey).getBody();

            } catch (ExpiredJwtException e) {
                logger.warn(String.format("Token API expired. Request by %s with token %s", wrappedRequest.getRemoteAddr(), apiKey));
                msgError = "Token API expired!";
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            } catch (MalformedJwtException e) {
                logger.warn(String.format("Malformed Token API. Request by %s with token %s", wrappedRequest.getRemoteAddr(), apiKey));
                msgError = "Malformed Token API";
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } catch (IllegalArgumentException e) {
                logger.warn(String.format("Request by %s with Token API not present", wrappedRequest.getRemoteAddr()));
                msgError = "Missing API Token attribute";
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } catch (RuntimeException e) {
                logger.error(e);
                e.printStackTrace();
                msgError = "Malformed Token API";
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }

        if (msgError.isEmpty()) {
            filterChain.doFilter(wrappedRequest, response);
        } else {
            response.getWriter().write(createJsonMessage(Map.of("error", msgError)));
        }
    }
}
