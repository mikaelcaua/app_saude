package devmikael.app_saude.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import devmikael.app_saude.models.House;
import jakarta.transaction.Transactional;

@Repository
public interface HouseRepository extends JpaRepository<House, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM house")
    List<House> getAllHouses();

    @Query(value = "SELECT * FROM house WHERE id = :id LIMIT 1", nativeQuery = true)
    House getSpecificHouseInformations(@Param("id") int id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = """
                INSERT INTO house(latitude, longitude, house_owner, id_heath_agent)
                VALUES(:latitude, :longitude, :house_owner, :id_heath_agent)
            """)
    Integer addHouse(
            @Param("latitude") Float latitude,
            @Param("longitude") Float longitude,
            @Param("house_owner") String houseOwner,
            @Param("id_heath_agent") Integer idHealthAgent);

    @Query("SELECT COUNT(h) > 0 FROM House h WHERE h.latitude = :latitude AND h.longitude = :longitude")
    boolean existsByCoordinates(@Param("latitude") float latitude, @Param("longitude") float longitude);

}
