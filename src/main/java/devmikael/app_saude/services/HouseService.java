package devmikael.app_saude.services;

import devmikael.app_saude.exceptions.DuplicateHouseLocationException;
import devmikael.app_saude.exceptions.HouseNotFoundException;
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
        House house = houseRepository.getSpecificHouseInformations(id);
        if (house == null) {
            throw new HouseNotFoundException(id);
        }
        return house;
    }

    public boolean registerHouse(float latitude, float longitude, String houseOwner, Integer idHealthAgent) {
        if (houseRepository.existsByCoordinates(latitude, longitude)) {
            throw new DuplicateHouseLocationException();
        }

        int inserted = houseRepository.addHouse(latitude, longitude, houseOwner, idHealthAgent);
        return inserted > 0;
    }
}
