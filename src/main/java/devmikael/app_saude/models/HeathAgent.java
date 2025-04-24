package devmikael.app_saude.models;
import java.util.List;

public class HeathAgent {
    private String name;
    private List<House> houses;
    private int id;

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
