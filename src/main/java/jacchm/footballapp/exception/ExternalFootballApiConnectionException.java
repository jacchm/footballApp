package jacchm.footballapp.exception;

public class ExternalFootballApiConnectionException extends RuntimeException {

    private final String errCode;

    public ExternalFootballApiConnectionException(String message, String errCode) {
        super(message);
        this.errCode = errCode;
    }

}
