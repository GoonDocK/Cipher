package Miscellaneous.Exceptions;
public class TooShortKey extends RuntimeException {
    public TooShortKey(String message) {
        super(message);
    }
}
