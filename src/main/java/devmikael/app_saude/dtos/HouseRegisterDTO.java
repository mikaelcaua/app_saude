package devmikael.app_saude.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HouseRegisterDTO {
    private float latitude;
    private float longitude;

    @JsonProperty("house_owner")
    private String houseOwner;


    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getHouseOwner() {
        return houseOwner;
    }
}
