package jacchm.footballapp.exception;

import lombok.Getter;

@Getter
public class FootballAppException extends RuntimeException {

    private final ErrCode errCode;

    public FootballAppException(ErrCode errCode) {
        super(errCode.getErrMessage());
        this.errCode = errCode;
    }

}
