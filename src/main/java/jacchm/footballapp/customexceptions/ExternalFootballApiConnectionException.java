package jacchm.footballapp.customexceptions;

public class ExternalFootballApiConnectionException extends RuntimeException {

    public ExternalFootballApiConnectionException(String message) {
        super(message);
    }

}
