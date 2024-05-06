package kr.or.kosa.nux2.web.common.response;

import kr.or.kosa.nux2.web.common.code.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;


@Getter
@NoArgsConstructor
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String divisionCode;
    private String resultMsg;
    Map<String, String> errors;
    private String reason;

    @Builder
    protected ErrorResponse(ErrorCode code, Map<String, String> errors) {
        this.timestamp = LocalDateTime.now();
        this.resultMsg = code.getMessage();
        this.status = code.getStatus();
        this.divisionCode = code.getDivisionCode();
        this.errors = errors;
    }

    @Builder
    protected ErrorResponse(ErrorCode code, String reason) {
        this.timestamp = LocalDateTime.now();
        this.resultMsg = code.getMessage();
        this.status = code.getStatus();
        this.divisionCode = code.getDivisionCode();
        this.reason = reason;
    }

    public static ErrorResponse of(ErrorCode code, Map<String, String> errors) {
        return new ErrorResponse(code, errors);
    }

    public static ErrorResponse of(ErrorCode code, String reason) {
        return new ErrorResponse(code, reason.split(":")[0]);
    }
}
