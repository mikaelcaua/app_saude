package devmikael.app_saude.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import devmikael.app_saude.models.House;

@Repository
public interface HouseRepository extends JpaRepository<House, Integer> {

    @Query(value = "SELECT * FROM house WHERE id_heath_agent = :heathAgentId", nativeQuery = true)
    List<House> getAllHousesForOneAgent(@Param("heathAgentId") int heathAgentId);

}
