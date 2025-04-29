package devmikael.app_saude.exceptions;

public class HouseNotFoundException extends RuntimeException {
    public HouseNotFoundException(int id) {
        super("Casa com id " + id + " não encontrada.");
    }
}
