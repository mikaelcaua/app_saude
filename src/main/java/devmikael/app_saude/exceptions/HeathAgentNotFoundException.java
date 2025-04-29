package devmikael.app_saude.exceptions;

public class HeathAgentNotFoundException extends RuntimeException {
    public HeathAgentNotFoundException(int id) {
        super("Agente de saúde não encontrado com id: " + id);
    }
    public HeathAgentNotFoundException(String detail) {
        super("Agente de saúde não encontrado " + detail);
    }
}