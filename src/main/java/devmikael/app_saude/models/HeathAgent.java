package devmikael.app_saude.models;
import java.util.List;
import jakarta.persistence.*;


@Entity
@Table(name = "heath_agent")
public class HeathAgent {

    @Id
    private int id;

    private String name;

    @OneToMany(mappedBy = "heathAgent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<House> houses;

    public HeathAgent() {} 

    public HeathAgent(String name, List<House> houses, int id){
        this.name = name;
        this.houses = houses;
        this.id = id;
    }

    @Override
    public String toString() {
        return "HeathAgent [name=" + name + ", houses=" + houses + ", id=" + id + "]";
    }
}
