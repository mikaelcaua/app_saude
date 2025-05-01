package devmikael.app_saude.dtos;

public class HeathAgentLoginResponseDTO {
    private String token;
    private Integer id;

    public HeathAgentLoginResponseDTO(String token, Integer id) {
        this.token = token;
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public Integer getId() {
        return id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
