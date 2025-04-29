package devmikael.app_saude.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import devmikael.app_saude.dtos.HeathAgentLoginRequestDTO;
import devmikael.app_saude.dtos.HeathAgentSignUpRequestDTO;
import devmikael.app_saude.services.HeathAgentService;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final HeathAgentService service;

    public AuthController(HeathAgentService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody HeathAgentLoginRequestDTO loginRequest) {
        service.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        Map<String, String> body = new HashMap<>();
        body.put("message", "Login bem-sucedido");
        return ResponseEntity.ok(body);
    }

    @PostMapping("/signUp")
    public ResponseEntity<Map<String, String>> signUp(@RequestBody HeathAgentSignUpRequestDTO entity) {
        service.registerHeathAgent(entity);
        Map<String, String> body = new HashMap<>();
        body.put("message", "Cadastro bem-sucedido");
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }
}