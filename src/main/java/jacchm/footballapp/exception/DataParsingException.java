package jacchm.footballapp.exception;

import lombok.Getter;

@Getter
public class DataParsingException extends RuntimeException {

    private final String errCode;

    public DataParsingException(String message, String errCode) {
        super(message);
        this.errCode = errCode;
    }

}
