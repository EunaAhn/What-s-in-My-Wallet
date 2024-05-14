package kr.or.kosa.nux2.web.common.response;

import kr.or.kosa.nux2.web.common.code.SuccessCode;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiResponse<T> {
    // API 응답 결과 Response
    private T result;

    // API 응답 코드 Response
    private String resultCode;

    // API 응답 코드 Message
    private String resultMsg;

    @Builder
    public ApiResponse(T result, SuccessCode successCode) {
        this.result = result;
        this.resultCode = successCode.getCode();
        this.resultMsg = successCode.getMessage();
    }
}
