package devmikael.app_saude.services;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import devmikael.app_saude.models.HeathAgent;
import devmikael.app_saude.models.House;
import devmikael.app_saude.repositories.HeathAgentRepository;

@Service
public class HeathAgentService {
    private HeathAgentRepository heathAgentRepository;
    
    public HeathAgentService(HeathAgentRepository heathAgentRepository) {
        this.heathAgentRepository = heathAgentRepository;
    }

    public List<House> getAllHousesForOneHeathAgent(int id) {
        return heathAgentRepository.getAllHousesForOneHeathAgent(id);
    }

    public List<HeathAgent> getAllHeathAgents(){
        return heathAgentRepository.getAllHeathAgents();
    }

    public Optional<HeathAgent> getHeathAgentByEmail(@Param("email") String email){
        return heathAgentRepository.getHeathAgentByEmail(email);
    }
}
