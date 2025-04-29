package devmikael.app_saude.models;
import java.util.List;
import jakarta.persistence.*;


@Entity
@Table(name = "heath_agent")
public class HeathAgent {

    @Id
    private int id;

    private String name;

    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @OneToMany(mappedBy = "heathAgent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<House> houses;

    public HeathAgent() {} 

    public HeathAgent(String name, List<House> houses, int id, String email, String password){
        this.name = name;
        this.houses = houses;
        this.id = id;
        this.email = email;
        this.password = password;

    }
}
