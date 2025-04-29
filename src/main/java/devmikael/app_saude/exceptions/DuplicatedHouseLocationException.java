package devmikael.app_saude.exceptions;

public class DuplicatedHouseLocationException extends RuntimeException {
    public DuplicatedHouseLocationException() {
        super("Já existe uma casa cadastrada nessa localização.");
    }
}
