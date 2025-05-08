package devmikael.app_saude.exceptions;

public class WeakPasswordException extends RuntimeException {
    public WeakPasswordException() {
        super("A senha deve ter pelo menos 8 caracteres.");
    }

    public WeakPasswordException(String message) {
        super(message);
    }
}