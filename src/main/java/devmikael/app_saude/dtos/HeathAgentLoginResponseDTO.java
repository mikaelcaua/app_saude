package devmikael.app_saude.dtos;

public class HeathAgentLoginResponseDTO {
    private String token;
    private Integer id;
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public HeathAgentLoginResponseDTO(String token, Integer id, String name, String email) {
        this.token = token;
        this.id = id;
        this.name = name;
        this.email = email;
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
