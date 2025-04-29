package devmikael.app_saude.dtos;

import devmikael.app_saude.models.HeathAgent;

public class HeathAgentWithoutPasswordDTO {
    private int id;

    private String name;

    private String email;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public HeathAgentWithoutPasswordDTO(HeathAgent heathAgent) {
        this.id = heathAgent.getId();
        this.name = heathAgent.getName();
        this.email = heathAgent.getEmail();
    }
}
