package callcenter.exception;

public class OutOfScopeException extends IllegalArgumentException{

    public OutOfScopeException(String message) {
        super(message);
    }
}
