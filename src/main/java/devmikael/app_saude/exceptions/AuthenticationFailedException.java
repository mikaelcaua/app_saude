package devmikael.app_saude.exceptions;

public class AuthenticationFailedException extends RuntimeException {
    public AuthenticationFailedException() {
        super("Usuário ou senha inválidos");
    }
}