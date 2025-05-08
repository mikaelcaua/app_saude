package devmikael.app_saude.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import devmikael.app_saude.dtos.HeathAgentSignUpRequestDTO;
import devmikael.app_saude.exceptions.DuplicateHeathAgentException;
import devmikael.app_saude.exceptions.HeathAgentNotFoundException;
import devmikael.app_saude.exceptions.InvalidEmailException;
import devmikael.app_saude.exceptions.WeakPasswordException;
import devmikael.app_saude.exceptions.AuthenticationFailedException;
import devmikael.app_saude.models.HeathAgent;
import devmikael.app_saude.models.House;
import devmikael.app_saude.repositories.HeathAgentRepository;

@Service
public class HeathAgentService {
    private final HeathAgentRepository repository;
    private final PasswordService passwordService;

    public HeathAgentService(HeathAgentRepository repository, PasswordService passwordService) {
        this.repository = repository;
        this.passwordService = passwordService;
    }

    public List<House> getAllHousesForOneHeathAgent(int id) {
        if (!repository.existsById(id)) {
            throw new HeathAgentNotFoundException(id);
        }
        return repository.getAllHousesForOneHeathAgent(id);
    }

    public List<HeathAgent> getAllHeathAgents() {
        return repository.getAllHeathAgents();
    }

    public HeathAgent getHeathAgentByEmail(String email) {
        return repository.getHeathAgentByEmail(email)
                         .orElseThrow(() -> new HeathAgentNotFoundException("com email: " + email));
    }

    public void registerHeathAgent(HeathAgentSignUpRequestDTO entity) {
    // 1. Validação de e‑mail
    String email = entity.getEmail();
    if (email == null || email.isBlank()) {
        throw new InvalidEmailException("O e‑mail não pode ser vazio.");
    }
    if (!email.matches("^[\\w\\-\\.]+@([\\w\\-]+\\.)+[\\w\\-]{2,4}$")) {
        throw new InvalidEmailException("Formato de e‑mail inválido.");
    }

    // 2. Validação de senha
    String password = entity.getPassword();
    if (password == null || password.isBlank()) {
        throw new WeakPasswordException("A senha não pode ser vazia.");
    }
    if (password.length() < 8) {
        throw new WeakPasswordException("A senha deve ter pelo menos 8 caracteres.");
    }

    // 3. Verificar duplicação
    if (repository.getHeathAgentByEmail(email).isPresent()) {
        throw new DuplicateHeathAgentException(email);
    }

    // 4. Hash da senha e persistência
    String hashed = passwordService.hash(password);
    repository.addHeathAgent(entity.getName(), email, hashed);
}

    public HeathAgent authenticate(String email, String password) {
        HeathAgent agent = repository.getHeathAgentByEmail(email)
                .orElseThrow(() -> new AuthenticationFailedException());
        if (!passwordService.verify(password, agent.getPassword())) {
            throw new AuthenticationFailedException();
        }
        return agent;
    }

    public HeathAgent getHeathAgentById(Integer id){
        Optional<HeathAgent> heathAgent =  repository.findById(id);

        if(heathAgent.isEmpty()){
            throw new HeathAgentNotFoundException(id);
        }

        return heathAgent.get();
    }
}