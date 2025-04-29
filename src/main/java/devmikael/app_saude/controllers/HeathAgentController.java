package devmikael.app_saude.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import devmikael.app_saude.dtos.HeathAgentWithoutPasswordDTO;
import devmikael.app_saude.dtos.HouseWithoutHeathAgentDTO;
import devmikael.app_saude.models.HeathAgent;
import devmikael.app_saude.services.HeathAgentService;


@RestController
public class HeathAgentController {
    private HeathAgentService heathAgentService;

    
    @GetMapping("heath_agents/{id}/houses")
    public List<HouseWithoutHeathAgentDTO> getAllHousesForOneHeathAgent(@PathVariable int id) {
        return heathAgentService.getAllHousesForOneHeathAgent(id).stream()
                .map(HouseWithoutHeathAgentDTO::new)
                .toList();
    }

    @GetMapping("heath_agents")
    public List<HeathAgentWithoutPasswordDTO> getAllHeathAgent() {
        return heathAgentService.getAllHeathAgents().stream()
        .map( (HeathAgent h) -> new HeathAgentWithoutPasswordDTO(h))
        .toList();
    }
}
