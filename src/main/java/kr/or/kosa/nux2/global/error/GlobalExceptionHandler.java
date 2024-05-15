package kr.or.kosa.nux2.global.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import kr.or.kosa.nux2.web.common.code.ErrorCode;
import kr.or.kosa.nux2.web.common.response.ErrorResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
//개발 완료 후 주석 해제 권장
public class GlobalExceptionHandler {
    private final HttpStatus HTTP_BADREQUEST = HttpStatus.BAD_REQUEST;

    // 비즈니스 로직 에러
    @ExceptionHandler(BusinessExceptionHandler.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessExceptionHandler e) {
        ErrorResponse response = ErrorResponse.of(ErrorCode.BUSINESS_EXCEPTION_ERROR, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // 비즈니스 로직예외와 공존이 권장?
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDatabaseException(DataAccessException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    // 객체, 파라미터 에러
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        BindingResult bindingResult = e.getBindingResult();

        Map<String , String> errorMap = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ErrorResponse response = ErrorResponse.of(ErrorCode.NOT_VALID_ERROR, errorMap);
        return new ResponseEntity<>(response, HTTP_BADREQUEST);
    }

    // 헤더데이터 에러
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponse> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
        ErrorResponse response = ErrorResponse.of(ErrorCode.REQUEST_BODY_MISSING_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, HTTP_BADREQUEST);
    }

    //객체 x
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {

        ErrorResponse response = ErrorResponse.of(ErrorCode.REQUEST_BODY_MISSING_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, HTTP_BADREQUEST);
    }

    //파라미터 x
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> handleMissingRequestHeaderExceptionException(
            MissingServletRequestParameterException ex) {

        ErrorResponse response = ErrorResponse.of(ErrorCode.MISSING_REQUEST_PARAMETER_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, HTTP_BADREQUEST);
    }


    // 잘못된서버요청
    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(HttpClientErrorException e) {
        ErrorResponse response = ErrorResponse.of(ErrorCode.BAD_REQUEST_ERROR, e.getMessage());
        return new ResponseEntity<>(response, HTTP_BADREQUEST);
    }


    // 주소에러
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundExceptionException(NoHandlerFoundException e) {
        ErrorResponse response = ErrorResponse.of(ErrorCode.NOT_FOUND_ERROR, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    //NPE
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerException(NullPointerException e) {
        ErrorResponse response = ErrorResponse.of(ErrorCode.NULL_POINT_ERROR, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    //IOException
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorResponse> handleIOException(IOException ex) {
        ErrorResponse response = ErrorResponse.of(ErrorCode.IO_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, HTTP_BADREQUEST);
    }



    //com.fasterxml.jackson.core Exception
    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ErrorResponse> handleJsonProcessingException(JsonProcessingException ex) {
        ErrorResponse response = ErrorResponse.of(ErrorCode.REQUEST_BODY_MISSING_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, HTTP_BADREQUEST);
    }


    // 나머지 예외
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex) {
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
