package devmikael.app_saude.controllers;

import devmikael.app_saude.dtos.HouseRegisterDTO;
import devmikael.app_saude.dtos.HouseWithoutHeathAgentDTO;
import devmikael.app_saude.models.House;
import devmikael.app_saude.services.HouseService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/houses")
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("")
    public List<HouseWithoutHeathAgentDTO> getAllHouses() {
        return houseService.getAllHouses().stream()
                .map(HouseWithoutHeathAgentDTO::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSpecificHouseInformations(@PathVariable int id) {
        House house = houseService.getSpecificHouseInformations(id);
        return ResponseEntity.ok(new HouseWithoutHeathAgentDTO(house));
    }

    @PostMapping("")
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
