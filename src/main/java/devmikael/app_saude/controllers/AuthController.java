package devmikael.app_saude.controllers;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devmikael.app_saude.dtos.LoginRequest;
import devmikael.app_saude.models.HeathAgent;
import devmikael.app_saude.services.HeathAgentService;
import devmikael.app_saude.services.PasswordService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final HeathAgentService heathAgentService;
    private final PasswordService passwordService;

    public AuthController(HeathAgentService heathAgentService, PasswordService passwordService) {
        this.heathAgentService = heathAgentService;
        this.passwordService = passwordService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<HeathAgent> heathAgentOpt = heathAgentService.getHeathAgentByEmail(loginRequest.getEmail());

        if (heathAgentOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
        }

        HeathAgent heathAgent = heathAgentOpt.get();

        if (!passwordService.verify(loginRequest.getPassword(), heathAgent.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
        }

        return ResponseEntity.ok("Login bem-sucedido");
    }
}
