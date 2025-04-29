package devmikael.app_saude.controllers;

import org.springframework.web.bind.annotation.RestController;

import devmikael.app_saude.dtos.HouseRegisterDTO;
import devmikael.app_saude.dtos.HouseWithoutHeathAgentDTO;
import devmikael.app_saude.services.HouseService;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class HouseController {

    private HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("/houses")
    public List<HouseWithoutHeathAgentDTO> getAllHouses() {
        return houseService.getAllHouses().stream()
                .map(HouseWithoutHeathAgentDTO::new)
                .toList();
    }

    @GetMapping("/houses/{id}")
    public HouseWithoutHeathAgentDTO getSpecificHouseInformations(@PathVariable int id) {
        return new HouseWithoutHeathAgentDTO(houseService.getSpecificHouseInformations(id));
    }

    @PostMapping("/houses")
    public ResponseEntity<?> registerHouse(@RequestBody HouseRegisterDTO entity) {

        boolean created = houseService.registerHouse(
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getHouseOwner(),
                entity.getIdHealthAgent());

        if (!created) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Já existe uma casa cadastrada nessa localização.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Cadastro bem-sucedido");
    }

}
