package Miscellaneous.Exceptions;

public class NotPrivateKey extends RuntimeException {
    public NotPrivateKey(String message) {
        super(message);
    }
}
