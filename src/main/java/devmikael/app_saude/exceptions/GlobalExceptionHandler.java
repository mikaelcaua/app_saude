package devmikael.app_saude.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HeathAgentNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(HeathAgentNotFoundException ex, HttpServletRequest req) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", ex.getMessage());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("path", req.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HouseNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(HouseNotFoundException ex, HttpServletRequest req) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", ex.getMessage());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("path", req.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateHeathAgentException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicate(DuplicateHeathAgentException ex,
            HttpServletRequest req) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", ex.getMessage());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("path", req.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateHouseLocationException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicate(DuplicateHouseLocationException ex,
            HttpServletRequest req) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", ex.getMessage());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("path", req.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<Map<String, Object>> handleAuthFailed(AuthenticationFailedException ex,
            HttpServletRequest req) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", ex.getMessage());
        body.put("status", HttpStatus.UNAUTHORIZED.value());
        body.put("path", req.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TokenMissingException.class)
    public ResponseEntity<Map<String, Object>> handleTokenMissing(TokenMissingException ex, HttpServletRequest req) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", ex.getMessage());
        body.put("status", HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TokenInvalidException.class)
    public ResponseEntity<Map<String, Object>> handleTokenInvalid(TokenInvalidException ex, HttpServletRequest req) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", ex.getMessage());
        body.put("status", HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> handleAccessDenied(AccessDeniedException ex, HttpServletRequest req) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", ex.getMessage());
        body.put("status", HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex, HttpServletRequest req) {
        Map<String, Object> body = new HashMap<>();
        body.put("error", "Erro interno do servidor");
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
