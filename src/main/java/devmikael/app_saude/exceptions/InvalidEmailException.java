package devmikael.app_saude.exceptions;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException() {
        super("E-mail inválido.");
    }

    public InvalidEmailException(String message) {
        super(message);
    }
}
