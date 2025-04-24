package devmikael.app_saude.dtos;
import devmikael.app_saude.models.House;

public class HouseWithoutHeathAgentDTO {
    private int id;
    private float latitude;
    private float longitude;
    private String houseOwner;

    public int getId() {
        return id;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getHouseOwner() {
        return houseOwner;
    }

    public HouseWithoutHeathAgentDTO(House house) {
        this.id = house.getId();
        this.latitude = house.getLatitude();
        this.longitude = house.getLongitude();
        this.houseOwner = house.getHouseOwner();
    }
}
