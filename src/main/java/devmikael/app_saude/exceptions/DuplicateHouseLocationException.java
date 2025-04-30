package devmikael.app_saude.exceptions;

public class DuplicateHouseLocationException extends RuntimeException {
    public DuplicateHouseLocationException() {
        super("Já existe uma casa cadastrada nessa localização.");
    }
}
