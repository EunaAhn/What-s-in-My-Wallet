package kr.or.kosa.nux2.web.common.code;

import lombok.Getter;

@Getter
public enum ErrorCode {
    /**
     * ******************************* Global Error CodeList ***************************************
     * HTTP Status Code
     * 400 : Bad Request
     * 401 : Unauthorized
     * 403 : Forbidden
     * 404 : Not Found
     * 500 : Internal Server Error
     * *********************************************************************************************
     */
    // 잘못된 서버 요청
    BAD_REQUEST_ERROR(400, "G001", "Bad Request Exception"),

    // @RequestBody 데이터 미 존재
    REQUEST_BODY_MISSING_ERROR(400, "G002", "Required request body is missing"),

    // 유효하지 않은 타입
    INVALID_TYPE_VALUE(400, "G003", " Invalid Type Value"),

    // Request Parameter 로 데이터가 전달되지 않을 경우
    MISSING_REQUEST_PARAMETER_ERROR(400, "G004", "Missing Servlet RequestParameter Exception"),

    // 입력/출력 값이 유효하지 않음
    IO_ERROR(400, "G005", "I/O Exception"),

    // com.fasterxml.jackson.core Processing Error
    JACKSON_PROCESS_ERROR(400, "G007", "JSON parsing Exception"),

    // 권한이 없음
    FORBIDDEN_ERROR(403, "G008", "Forbidden Exception"),

    // 서버로 요청한 리소스가 존재하지 않음
    NOT_FOUND_ERROR(404, "G009", "Not Found Exception"),

    // NULL Point Exception 발생
    NULL_POINT_ERROR(404, "G010", "Null Point Exception"),

    // @RequestBody 및 @RequestParam, @PathVariable 값이 유효하지 않음
    NOT_VALID_ERROR(400, "G011", "handle Validation Exception"),

    // @RequestBody 및 @RequestParam, @PathVariable 값이 유효하지 않음
    NOT_VALID_HEADER_ERROR(400, "G012", "Not Valid Header Exception"),

    // 서버가 처리 할 방법을 모르는 경우 발생
    INTERNAL_SERVER_ERROR(500, "G999", "Internal Server Error Exception"),


    /**
     * ******************************* Custom Error CodeList ***************************************
     */
    USER_NOT_FOUND(404, "", "User Not Found"),


    CARD_NOT_FOUND(404, "", "Card Not Found"),


    CARDLIST_NOT_FOUND(404, "", "List Not Found"),


    CHECKLIST_NOT_FOUND(404, "", "Checklist Not Found"),


    COMMENT_NOT_FOUND(404, "", "Comment Not Found"),


    TODOCHECK_NOT_FOUND(404, "", "TodoCheck Not Found"),


    BOARD_NOT_FOUND(404, "", "Board Not Found"),


    MEETINGTIME_NOT_FOUND(404, "", "MeetineTime Not Found"),


    BUSINESS_EXCEPTION_ERROR(200, "B999", "Business Exception Error"),



//    // Transaction Insert Error
//    INSERT_ERROR(200, "9999", "Insert Transaction Error Exception"),
//
//    // Transaction Update Error
//    UPDATE_ERROR(200, "9999", "Update Transaction Error Exception"),
//
//    // Transaction Delete Error
//    DELETE_ERROR(200, "9999", "Delete Transaction Error Exception"),
    ; // End

    /**
     * ******************************* Error Code Constructor ***************************************
     */
    // 에러 코드의 '코드 상태'을 반환한다.
    private final int status;

    // 에러 코드의 '코드간 구분 값'을 반환한다.
    private final String divisionCode;

    // 에러 코드의 '코드 메시지'을 반환한다.
    private final String message;

    // 생성자 구성
    ErrorCode(final int status, final String divisionCode, final String message) {
        this.status = status;
        this.divisionCode = divisionCode;
        this.message = message;
    }
}
