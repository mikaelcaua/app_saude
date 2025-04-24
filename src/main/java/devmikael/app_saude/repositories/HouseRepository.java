package devmikael.app_saude.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import devmikael.app_saude.models.House;

@Repository
public class HouseRepository {

    List<House> mock = new ArrayList<House>();

    public List<House> getAllHousesForOneAgent(int id){
        return mock;
    }

}
