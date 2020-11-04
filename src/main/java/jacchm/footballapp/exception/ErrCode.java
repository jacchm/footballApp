package jacchm.footballapp.exception;

import lombok.Getter;

@Getter
public enum ErrCode {

    ERR0001("External Football API connection exception."),
    ERR0002("Json parsing exception."),
    ERR0003("Json parsing exception, no information about competitionId.");

    private final String errMessage;

    ErrCode(String errMessage) {
        this.errMessage = errMessage;
    }

}
