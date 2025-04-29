package devmikael.app_saude.exceptions;

public class DuplicateHeathAgentException extends RuntimeException {
    public DuplicateHeathAgentException(String email) {
        super("Já existe um agente de saúde cadastrado com o email: " + email);
    }
}