package devmikael.app_saude.controllers;
import org.springframework.web.bind.annotation.RestController;
import devmikael.app_saude.dtos.HouseWithoutHeathAgentDTO;
import devmikael.app_saude.services.HouseService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class HouseController {

    private HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("/agents/{id}/houses")
    public List<HouseWithoutHeathAgentDTO> getAllHousesForOneHeathAgent(@PathVariable int id) {
        return houseService.getAllHousesForOneHeathAgent(id).stream()
        .map(HouseWithoutHeathAgentDTO::new)
        .toList();
    }

}
