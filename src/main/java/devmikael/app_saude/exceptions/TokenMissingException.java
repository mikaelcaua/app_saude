package devmikael.app_saude.exceptions;

public class TokenMissingException extends RuntimeException {
    public TokenMissingException(String message) {
        super(message);
    }
}