package kr.or.kosa.nux2.global.error;

import kr.or.kosa.nux2.web.common.code.ErrorCode;
import lombok.Builder;
import lombok.Getter;

public class BusinessExceptionHandler extends RuntimeException{
    @Getter
    private final ErrorCode errorCode;

    @Builder
    public BusinessExceptionHandler(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
