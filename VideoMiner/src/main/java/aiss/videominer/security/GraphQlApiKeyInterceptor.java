package aiss.videominer.security;

import aiss.videominer.exception.UnauthorizedGraphQLException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class GraphQlApiKeyInterceptor implements WebGraphQlInterceptor {

    @Value("${videominer.api.key}")
    private String apiKey;

    @Value("${videominer.api.key.enabled:true}")
    private boolean isApiKeyEnabled;

    @NonNull
    @Override
    public Mono<WebGraphQlResponse> intercept(@NonNull WebGraphQlRequest request, @NonNull Chain chain) {
        String document = request.getDocument();
        
        // Comprobamos si la petición GraphQL es una mutación
        if (isApiKeyEnabled && document.trim().startsWith("mutation")) {
            List<String> authHeaders = request.getHeaders().get("Authorization");
            boolean hasValidKey = false;

            if (authHeaders != null && !authHeaders.isEmpty()) {
                String authHeader = authHeaders.get(0);
                if (authHeader.startsWith("Bearer ")) {
                    String token = authHeader.substring(7);
                    if (apiKey.equals(token)) {
                        hasValidKey = true;
                    }
                }
            }

            if (!hasValidKey) {
                // Si no tiene API Key válida, devolvemos un error y cortamos la cadena
                return Mono.error(new UnauthorizedGraphQLException("No autorizado: falta la API Key en los headers"));
            }
        }
        
        // Si no es mutación, o si la key es válida o si la autenticación está desactivada, continuamos con la ejecución
        return chain.next(request);
    }
}
