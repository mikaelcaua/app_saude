package devmikael.app_saude.controllers;

import devmikael.app_saude.dtos.HouseRegisterDTO;
import devmikael.app_saude.dtos.HouseWithoutHeathAgentDTO;
import devmikael.app_saude.models.House;
import devmikael.app_saude.services.HouseService;
import devmikael.app_saude.exceptions.HouseNotFoundException;
import java.util.List;
import java.util.Map;
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
        try {
            House house = houseService.getSpecificHouseInformations(id);
            return ResponseEntity.ok(new HouseWithoutHeathAgentDTO(house));
        } catch (HouseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("")
    public ResponseEntity<?> registerHouse(@RequestBody HouseRegisterDTO entity) {
        boolean created = houseService.registerHouse(
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getHouseOwner(),
                entity.getIdHealthAgent());

        if (!created) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Já existe uma casa cadastrada nessa localização.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Cadastro bem-sucedido");
    }
}
