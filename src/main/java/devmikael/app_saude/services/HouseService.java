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

    public List<House> getAllHouses() {
        return houseRepository.getAllHouses();
    }

    public House getSpecificHouseInformations(int id) {
        return houseRepository.getSpecificHouseInformations(id);
    }

    public boolean registerHouse(float latitude, float longitude, String houseOwner, Integer idHealthAgent) {
        if (houseRepository.existsByCoordinates(latitude, longitude)) {
            return false;
        }

        int inserted = houseRepository.addHouse(latitude, longitude, houseOwner, idHealthAgent);
        return inserted > 0;
    }

}
