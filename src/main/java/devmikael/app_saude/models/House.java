package devmikael.app_saude.models;

public class House {
    private float latitude;
    private float longitude;
    private String houseOwner;
    private int id;

    public House(float latitude, float longitude, String houseOwner, int id){
        this.latitude = latitude;
        this.longitude = longitude;
        this.houseOwner = houseOwner;
        this.id = id;
    }

    @Override
    public String toString() {
        return "House [latitude=" + latitude + ", longitude=" + longitude + ", houseOwner=" + houseOwner + ", id=" + id
                + "]";
    }

}
