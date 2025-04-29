package devmikael.app_saude.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import devmikael.app_saude.models.HeathAgent;
import devmikael.app_saude.models.House;

@Repository
public interface HeathAgentRepository extends JpaRepository<HeathAgent, Integer>{
    
    @Query(nativeQuery =  true, value = "SELECT * FROM heath_agent")
    List<HeathAgent> getAllHeathAgents();

    @Query(value = "SELECT * FROM heath_agent WHERE email = :id LIMIT 1", nativeQuery = true)
    List<HeathAgent> getHeathAgentInformations(@Param("id") int id);

    @Query(value = "SELECT * FROM house WHERE id_heath_agent = :idHeathAgent", nativeQuery = true)
    List<House> getAllHousesForOneHeathAgent(@Param("idHeathAgent") int idHeathAgent);

    @Query(value = "SELECT * FROM heath_agent WHERE email = :email LIMIT 1", nativeQuery = true)
    Optional<HeathAgent> getHeathAgentByEmail(@Param("email") String email);

}
