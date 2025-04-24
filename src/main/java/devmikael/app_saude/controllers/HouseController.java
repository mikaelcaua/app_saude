package devmikael.app_saude.controllers;
import org.springframework.web.bind.annotation.RestController;
import devmikael.app_saude.models.House;
import devmikael.app_saude.repositories.HouseRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class HouseController {

    private HouseRepository houseRepository;

    public HouseController(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    @GetMapping("/agents/{id}/houses")
    public List<House> getAllHousesForOneAgent(@PathVariable int id) {
        return houseRepository.getAllHousesForOneAgent(id);
    }

}
