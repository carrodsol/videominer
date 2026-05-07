package aiss.videominer.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiKeyInterceptor implements HandlerInterceptor {
    // Leemos la clave que pusimos en el application.properties
    @Value("${videominer.api.key}")
    private String apiKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (apiKey.equals(token)) {
                return true;
            }
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("401 Unauthorized: API Key invalida o ausente");
        return false;
    }
}
