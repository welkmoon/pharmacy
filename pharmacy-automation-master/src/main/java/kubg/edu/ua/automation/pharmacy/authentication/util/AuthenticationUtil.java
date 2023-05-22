package kubg.edu.ua.automation.pharmacy.authentication.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationUtil {

    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    public static final String BEARER_TOKEN_PREFIX = "Bearer ";

    public static String extractAuthenticationHeader(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION_HEADER_NAME);
    }

    public static boolean isJwtToken(String authenticationHeader) {
        return StringUtils.startsWith(authenticationHeader, BEARER_TOKEN_PREFIX);
    }

    public static String extractJwtToken(String authenticationHeader) {
        return StringUtils.substring(authenticationHeader, BEARER_TOKEN_PREFIX.length());
    }
}
