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

        try {
            String path = request.getRequestURI();
            String header = request.getHeader("Authorization");

            if (path.startsWith("/auth")) {
                filterChain.doFilter(request, response);
                return;
            }

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

            if ((request.getMethod().equals("GET") || request.getMethod().equals("POST"))
                    && path.matches("^/heath_agents/\\d+/houses$")) {

                String[] parts = path.split("/");
                int pathId = Integer.parseInt(parts[2]);

                if (!userId.equals(pathId)) {
                    throw new AccessDeniedException("Você só pode acessar seus próprios dados");
                }

                // Adiciona o userId ao request para ser acessado no controlador
                request.setAttribute("id_heath_agent", userId);

                filterChain.doFilter(request, response);
                return;
            }

            throw new AccessDeniedException("Acesso negado a esta rota");

        } catch (TokenMissingException | TokenInvalidException e) {
            sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        } catch (AccessDeniedException e) {
            sendErrorResponse(response, HttpServletResponse.SC_FORBIDDEN, e.getMessage());
        }
    }

    private void sendErrorResponse(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.getWriter().write(
                String.format("{\"error\": \"%s\", \"status\": %d}", message, status));
    }

}
