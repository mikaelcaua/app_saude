package devmikael.app_saude.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import devmikael.app_saude.dtos.HeathAgentWithoutPasswordDTO;
import devmikael.app_saude.dtos.HouseWithoutHeathAgentDTO;
import devmikael.app_saude.services.HeathAgentService;

@RestController
public class HeathAgentController {

    private final HeathAgentService service;

    public HeathAgentController(HeathAgentService service) {
        this.service = service;
    }

    @GetMapping("/heath_agents/{id}/houses")
    public ResponseEntity<List<HouseWithoutHeathAgentDTO>> getAllHousesForOneHeathAgent(@PathVariable int id) {
        List<HouseWithoutHeathAgentDTO> houses = service.getAllHousesForOneHeathAgent(id)
            .stream()
            .map(HouseWithoutHeathAgentDTO::new)
            .toList();
        return ResponseEntity.ok(houses);
    }

    @GetMapping("/heath_agents")
    public ResponseEntity<List<HeathAgentWithoutPasswordDTO>> getAllHeathAgents() {
        List<HeathAgentWithoutPasswordDTO> agents = service.getAllHeathAgents()
            .stream()
            .map(HeathAgentWithoutPasswordDTO::new)
            .toList();
        return ResponseEntity.ok(agents);
    }
}
