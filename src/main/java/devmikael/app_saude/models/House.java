package devmikael.app_saude.models;
import jakarta.persistence.*;

@Entity
@Table(name = "house")
public class House {

    @Id
    private int id;

    private float latitude;
    private float longitude;

    @Column(name = "house_owner")
    private String houseOwner;

    @ManyToOne
    @JoinColumn(name = "id_heath_agent")
    private HeathAgent heathAgent;

    public House() {}

    public House(float latitude, float longitude, String houseOwner, int id){
        this.latitude = latitude;
        this.longitude = longitude;
        this.houseOwner = houseOwner;
        this.id = id;
    }

    @Override
    public String toString() {
        return "House [latitude=" + latitude + ", longitude=" + longitude + ", houseOwner=" + houseOwner + ", id=" + id + "]";
    }
}
