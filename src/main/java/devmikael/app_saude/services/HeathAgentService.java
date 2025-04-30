package devmikael.app_saude.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import devmikael.app_saude.dtos.HeathAgentSignUpRequestDTO;
import devmikael.app_saude.exceptions.DuplicateHeathAgentException;
import devmikael.app_saude.exceptions.HeathAgentNotFoundException;
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
        if (repository.getHeathAgentByEmail(entity.getEmail()).isPresent()) {
            throw new DuplicateHeathAgentException(entity.getEmail());
        }
        String hashed = passwordService.hash(entity.getPassword());
        repository.addHeathAgent(entity.getName(), entity.getEmail(), hashed);
    }

    public HeathAgent authenticate(String email, String password) {
        HeathAgent agent = repository.getHeathAgentByEmail(email)
                .orElseThrow(() -> new AuthenticationFailedException());
        if (!passwordService.verify(password, agent.getPassword())) {
            throw new AuthenticationFailedException();
        }
        return agent;
    }

    public boolean verifyPassword(String plain, String hashed) {
        return passwordService.verify(plain, hashed);
    }

    public HeathAgent getHeathAgentById(Integer id){
        Optional<HeathAgent> heathAgent =  repository.findById(id);

        if(heathAgent.isEmpty()){
            throw new HeathAgentNotFoundException(id);
        }

        return heathAgent.get();
    }
}