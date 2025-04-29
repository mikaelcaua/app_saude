package devmikael.app_saude.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import devmikael.app_saude.dtos.HeathAgentSignUpRequestDTO;
import devmikael.app_saude.models.HeathAgent;
import devmikael.app_saude.models.House;
import devmikael.app_saude.repositories.HeathAgentRepository;

@Service
public class HeathAgentService {
    private final HeathAgentRepository heathAgentRepository;
    private final PasswordService passwordService;

    public HeathAgentService(HeathAgentRepository heathAgentRepository, PasswordService passwordService) {
        this.heathAgentRepository = heathAgentRepository;
        this.passwordService = passwordService;
    }

    public List<House> getAllHousesForOneHeathAgent(int id) {
        return heathAgentRepository.getAllHousesForOneHeathAgent(id);
    }

    public List<HeathAgent> getAllHeathAgents() {
        return heathAgentRepository.getAllHeathAgents();
    }

    public Optional<HeathAgent> getHeathAgentByEmail(String email) {
        return heathAgentRepository.getHeathAgentByEmail(email);
    }

    public boolean registerHeathAgent(HeathAgentSignUpRequestDTO entity) {
        if (heathAgentRepository.getHeathAgentByEmail(entity.getEmail()).isPresent()) {
            return false;
        }

        String hashedPassword = passwordService.hash(entity.getPassword());

        heathAgentRepository.addHeathAgent(
                entity.getName(),
                entity.getEmail(),
                hashedPassword);

        return true;
    }

    public boolean verifyPassword(String plainPassword, String hashedPassword) {
        return passwordService.verify(plainPassword, hashedPassword);
    }
}
