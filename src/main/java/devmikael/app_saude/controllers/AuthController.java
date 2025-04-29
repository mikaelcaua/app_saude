package devmikael.app_saude.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import devmikael.app_saude.dtos.HeathAgentLoginRequestDTO;
import devmikael.app_saude.dtos.HeathAgentSignUpRequestDTO;
import devmikael.app_saude.models.HeathAgent;
import devmikael.app_saude.services.HeathAgentService;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final HeathAgentService heathAgentService;

    public AuthController(HeathAgentService heathAgentService) {
        this.heathAgentService = heathAgentService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody HeathAgentLoginRequestDTO loginRequest) {
        Optional<HeathAgent> heathAgentOpt = heathAgentService.getHeathAgentByEmail(loginRequest.getEmail());

        if (heathAgentOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
        }

        HeathAgent heathAgent = heathAgentOpt.get();

        if (!heathAgentService.verifyPassword(loginRequest.getPassword(), heathAgent.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos");
        }

        return ResponseEntity.ok("Login bem-sucedido");
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> insertHeathAgent(@RequestBody HeathAgentSignUpRequestDTO entity) {
        boolean created = heathAgentService.registerHeathAgent(entity);

        if (!created) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("E-mail já cadastrado");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Cadastro bem-sucedido");
    }
}
