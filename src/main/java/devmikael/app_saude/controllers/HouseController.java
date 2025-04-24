package devmikael.app_saude.controllers;
import org.springframework.web.bind.annotation.RestController;
import devmikael.app_saude.models.House;
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
    public List<House> getAllHousesForOneAgent(@PathVariable int id) {
        return houseService.getAllHousesForOneAgent(id);
    }

}
