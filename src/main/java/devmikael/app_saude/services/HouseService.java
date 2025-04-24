package devmikael.app_saude.services;
import devmikael.app_saude.models.House;
import devmikael.app_saude.repositories.HouseRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HouseService {

    private HouseRepository houseRepository;

    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public List<House> getAllHousesForOneAgent(int id) {
        return houseRepository.getAllHousesForOneAgent(id);
    }

}
