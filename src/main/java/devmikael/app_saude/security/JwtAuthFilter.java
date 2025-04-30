package devmikael.app_saude.security;
import devmikael.app_saude.exceptions.TokenMissingException;
import devmikael.app_saude.exceptions.TokenInvalidException;
import devmikael.app_saude.exceptions.AccessDeniedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        String header = request.getHeader("Authorization");

        // Libera rotas de autenticação sem precisar de token
        if (path.startsWith("/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Verifica se o token foi enviado corretamente
        if (header == null || !header.startsWith("Bearer ")) {
            throw new TokenMissingException("Token ausente ou mal formatado");
        }

        String token = header.substring(7);
        Integer userId;

        try {
            userId = JwtUtil.extractUserId(token);
        } catch (Exception e) {
            throw new TokenInvalidException("Token inválido");
        }

        // Libera acesso ao GET /heath_agents/{id}/houses APENAS se for o mesmo id do token
        if ((request.getMethod().equals("GET") || request.getMethod().equals("POST"))  && path.matches("^/heath_agents/\\d+/houses$")) {
            String[] parts = path.split("/");
            int pathId = Integer.parseInt(parts[2]);

            if (!userId.equals(pathId)) {
                throw new AccessDeniedException("Você só pode acessar seus próprios dados");
            }

            filterChain.doFilter(request, response); 
            return;
        }

        throw new AccessDeniedException("Acesso negado a esta rota");
    }
}
