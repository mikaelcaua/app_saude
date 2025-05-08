package devmikael.app_saude.models;

import devmikael.app_saude.exceptions.InvalidEmailException;
import devmikael.app_saude.exceptions.WeakPasswordException;
import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;

@Entity
@Table(name = "health_agent")
public class HeathAgent {

    @Id
    private int id;

    private String name;

    private String email;

    private String password;

    @OneToMany(mappedBy = "heathAgent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<House> houses;

    public HeathAgent() {}

    public HeathAgent(String name, List<House> houses, int id, String email, String password) {
        this.name = name;
        this.houses = houses;
        this.id = id;
        setEmail(email);
        setPassword(password);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new InvalidEmailException("O e-mail não pode ser vazio.");
        }
        if (!email.matches("^[\\w\\-\\.]+@([\\w\\-]+\\.)+[\\w\\-]{2,4}$")) {
            throw new InvalidEmailException("E-mail inválido.");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.isBlank()) {
            throw new WeakPasswordException("A senha não pode ser vazia.");
        }
        if (password.length() < 8) {
            throw new WeakPasswordException("A senha deve ter pelo menos 8 caracteres.");
        }
        this.password = new BCryptPasswordEncoder().encode(password);
    }
}
