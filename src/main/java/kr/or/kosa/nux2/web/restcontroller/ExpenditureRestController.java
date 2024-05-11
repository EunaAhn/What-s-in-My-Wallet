package kr.or.kosa.nux2.web.restcontroller;

import kr.or.kosa.nux2.domain.expenditure.dto.ExenditureDto;
import kr.or.kosa.nux2.domain.expenditure.service.ExpenditureService;
import kr.or.kosa.nux2.web.common.code.SuccessCode;
import kr.or.kosa.nux2.web.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expenditure")
public class ExpenditureRestController {
    private final ExpenditureService expenditureService;


    @GetMapping("/daily")
    public ResponseEntity<ApiResponse<ExenditureDto.DetailsReponse>> memberDailyExpenditureDetail(@RequestBody ExenditureDto.ExpenditureDetailRequest request) {
        ExenditureDto.DetailsReponse response = expenditureService.showMemberDailyExpenditureDetails(request);

        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @GetMapping("/totalexpenditure")
    public ResponseEntity<ApiResponse<List<ExenditureDto.TotalCount>>> totalExpenditure(@RequestBody ExenditureDto.TotalExpenditureCountRequest request) {
        List<ExenditureDto.TotalCount> responses = expenditureService.showTotalExpenditureByMonth(request);

        return new ResponseEntity<>(new ApiResponse<>(responses, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);

    }
    @GetMapping("/ratioByCategory")
    public ResponseEntity<ApiResponse<List<ExenditureDto.RatioByCategoryResponse>>> expenditureRatioByCategory(@RequestBody ExenditureDto.MonthRequest request) {
        Map<String, Object> map = new HashMap<>();
        // 현재 날짜 가져오기
        LocalDate currentDate = LocalDate.now();

        // 14개월 전 날짜 계산
        LocalDate fourteenMonthsAgo = currentDate.minusMonths(request.getMonth());

        // YYYY-MM 형식으로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate = fourteenMonthsAgo.format(formatter);


        map.put("month", formattedDate);
        map.put("memberId", "dnwo1111");
        List<ExenditureDto.RatioByCategoryResponse> response = expenditureService.showExpenditureRatioForCategoryByMonth(map);
        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @GetMapping("/averageExpenditureby12month")
    public ResponseEntity<ApiResponse<Map<String, Object>>> avgExpenditureForMonthByYear() {
        Map<String, Object> map = expenditureService.findAverageExpenditureForMonthByYear(2023);
        return new ResponseEntity<>(new ApiResponse<>(map, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @GetMapping("/totalExpenditure")
    public ResponseEntity<ApiResponse<Map<String, Object>>> test() {
        Map<String, Object> map = expenditureService.showTotalExpenditureForMonthAndTimeByYearAndMonth("2023-05");
        return new ResponseEntity<>(new ApiResponse<>(map, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @GetMapping("/expenditurecount")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> categoryCount() {
        List<Map<String, Object>> map = expenditureService.showExpenditureCountForCategoryByMonth(14);
        return new ResponseEntity<>(new ApiResponse<>(map, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

}
