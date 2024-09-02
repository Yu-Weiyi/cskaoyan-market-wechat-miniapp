package happy.coding.util;

import happy.coding.constant.ErrorCodeConstant;
import happy.coding.exception.AuthException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 为伊WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
 * @version 0.1
 * @project project-two
 * @package happy.coding.util
 * @name JwtUtil
 * @description JWT 工具类。
 * @since 2024-09-02 00:07
 */
@Component
@ConfigurationProperties(prefix = "jwt")
@Data
@NoArgsConstructor
public class JwtUtil {

    private String cookieKey;

    private String subject;
    private String audience;
    private String issuer;
    private int expirationTime;
    private String customIdKey;

    private String secretKey;

    /**
     * @name genToken
     * @description 生成 JWT 令牌，使用HS256算法。
     * @param id java.lang.String
     * @return String
     * @author WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
     * @since 2024-09-02, Mon, 00:22, CST
     */
    public String genToken(String id, Date now) {

        SecretKey hmacShaSecretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
        Map<String, String> claims = new HashMap<>();
        claims.put(customIdKey, id);
        String jwtToken = Jwts.builder()
                .header()
                .add("alg", "HS256")
                .add("typ", "JWT")
                .and()
                .subject(subject)
                .audience()
                .add(audience)
                .and()
                .issuer(issuer)
                .issuedAt(now)
                .expiration(Date.from(Instant.now().plusSeconds(expirationTime)))
                .claims(claims)
                .signWith(hmacShaSecretKey, Jwts.SIG.HS256)
                .compact();
        return jwtToken;
    }

    /**
     * @name extractClaims
     * @description 解析提取JWT令牌。
     * @param jwtToken java.lang.String
     * @return Jws<Claims>
     * @author WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
     * @since 2024-09-02, Mon, 02:05, CST
     */
    public Jws<Claims> extractClaims(String jwtToken) {

        SecretKey hmacShaSecretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.parser()
                .verifyWith(hmacShaSecretKey)
                .build()
                .parseSignedClaims(jwtToken);
    }

    /**
     * @name verifyValid
     * @description 验证JWT令牌是否有效，提取令牌中的用户ID。
     * @param jwtToken java.lang.String
     * @return void
     * @throws AuthException
     * @author WaYease <a href="mailto:yu_weiyi@outlook.com">yu_weiyi@outlook.com</a>
     * @since 2024-09-02, Mon, 02:33, CST
     */
    public String extractId(String jwtToken) throws AuthException {

        Claims payload = extractClaims(jwtToken).getPayload();

        // verify
        if (!subject.equals(payload.getSubject())) {
            throw new AuthException(ErrorCodeConstant.INVALID_TOKEN_SUBJECT);
        }
        if (!issuer.equals(payload.getIssuer())) {
            throw new AuthException(ErrorCodeConstant.INVALID_TOKEN_ISSUER);
        }
        if (!new Date().before(payload.getExpiration())) {
            throw new AuthException(ErrorCodeConstant.TOKEN_EXPIRED);
        }

        return (String) payload.get(customIdKey);
    }
}
