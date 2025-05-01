package devmikael.app_saude.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import devmikael.app_saude.dtos.HeathAgentWithoutPasswordDTO;
import devmikael.app_saude.dtos.HouseRegisterDTO;
import devmikael.app_saude.dtos.HouseWithoutHeathAgentDTO;
import devmikael.app_saude.services.HeathAgentService;
import devmikael.app_saude.services.HouseService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/heath_agents")
public class HeathAgentController {

    private final HeathAgentService heathAgentService;
    private final HouseService houseService;

    public HeathAgentController(HeathAgentService service, HouseService houseService) {
        this.heathAgentService = service;
        this.houseService = houseService;
    }



    @GetMapping("")
    public ResponseEntity<List<HeathAgentWithoutPasswordDTO>> getAllHeathAgents() {
        List<HeathAgentWithoutPasswordDTO> agents = heathAgentService.getAllHeathAgents()
            .stream()
            .map(HeathAgentWithoutPasswordDTO::new)
            .toList();
        return ResponseEntity.ok(agents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeathAgentWithoutPasswordDTO> getHeathAgentById(@PathVariable int id) {
        HeathAgentWithoutPasswordDTO heathAgent = new HeathAgentWithoutPasswordDTO(heathAgentService.getHeathAgentById(id));
        return ResponseEntity.ok(heathAgent);
    }

    @GetMapping("/{id}/houses")
    public ResponseEntity<List<HouseWithoutHeathAgentDTO>> getAllHousesForOneHeathAgent(@PathVariable int id) {
        List<HouseWithoutHeathAgentDTO> houses = heathAgentService.getAllHousesForOneHeathAgent(id)
            .stream()
            .map(HouseWithoutHeathAgentDTO::new)
            .toList();
        return ResponseEntity.ok(houses);
    }

    @PostMapping("/{id}/houses")
    public ResponseEntity<?> registerHouse(
            HttpServletRequest request,
            @RequestBody HouseRegisterDTO entity) {

        Integer agentId = (Integer) request.getAttribute("id_heath_agent");

        houseService.registerHouse(
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getHouseOwner(),
                agentId);

        return ResponseEntity.status(HttpStatus.CREATED).body("Cadastro bem-sucedido");
    }
}
