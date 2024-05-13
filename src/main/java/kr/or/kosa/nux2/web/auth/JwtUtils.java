package kr.or.kosa.nux2.web.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Slf4j
@Component
@RequiredArgsConstructor
@PropertySource("classpath:config/jwt.properties")
public class JwtUtils {
    @Value(value = "${jwt.prefix}")
    private String prefix;
    @Value(value = "${jwt.secret}")
    private String secretKey;
    @Value(value = "${jwt.token.access-expiration-time}")
    private Long accessExpirationTime;
    @Value(value = "${jwt.token.refresh-expiration-time}")
    private Long refreshExpirationTime;

    private final CustomUserDetailsService userDetailsService;


    /*
    Access 토큰 생성
     */
    public String createAccessToken(Authentication authentication) {
        log.info("method = {}","createAccessToken");
        Claims claims = Jwts.claims().setSubject(authentication.getName());
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + accessExpirationTime);

        return prefix+" "+Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setIssuer("2nux")
                .setExpiration(expireDate)
                .signWith(createSecretKey(secretKey),SignatureAlgorithm.HS256)
                .compact();
    }


    /*
    Refresh 토큰 생성
     */
    public String createRefreshToken(Authentication authentication) {
        log.info("method = {}","createRefreshToken");
        Claims claims = Jwts.claims().setSubject(authentication.getName());
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + refreshExpirationTime);

        return prefix+" "+Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setIssuer("2nux")
                .setExpiration(expireDate)
                .signWith(createSecretKey(secretKey),SignatureAlgorithm.HS256)
                .compact();
    }

    public Key createSecretKey(String secretKey){
        log.info("method = {}","createSecretKey");
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return key;
    }

    public String extractUsername(String token){
        log.info("method = {}","extractUsername");
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        log.info("method = {}","extractClaim");
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token){
        log.info("method = {}","extractAllClaims");
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8)).build()
                .parseClaimsJws(token).getBody();

    }
    /*
    토큰으로 클레임 생성, 이를 통해 Authentication 객체 반환
     */
    public Authentication getAuthentication(String token) {
        log.info("method = {}","getAuthentication");
        String userPrincipal = extractAllClaims(token).getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(userPrincipal);
        return new JwtAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
    //토큰 추출
    public String findToken(HttpServletRequest request) {
        log.info("method = {}","findToken");
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && !bearerToken.equalsIgnoreCase("")) {
            return bearerToken.split(" ")[1];
        }
        return null;
    }

    public boolean validateToken(String token){
        log.info("method = {}","validateToken");
        try{
            Claims claims = extractAllClaims(token);
            Date expirationDate = claims.getExpiration();
            Date now = new Date();

            if(expirationDate.before(now)){
                log.info("token expired");
                return false;
            }
            return true;
        } catch(JwtException e) {
            log.error(e.getMessage());
            return false;
        }

    }
}
