package kr.or.kosa.nux2.domain.expenditure.service;

import kr.or.kosa.nux2.domain.expenditure.dto.ExenditureDto;
import kr.or.kosa.nux2.domain.expenditure.repository.ExpenditureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExpenditureServiceImpl implements ExpenditureService{
    private final ExpenditureRepository expenditureRepository;

    @Override
    public List<ExenditureDto.Response> showMemberMonthlyExpenditures() {
        //멤버아이디 컨텍스트에서 받아오기
        //연월을 클라이언트로 부터 받아오기
        // 해당 쿼리를 불러오기
        LocalDate today = LocalDate.now();
        // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");         // 포맷 적용        String formatedNow = now.format(formatter);
        String nowDate = today.format(formatter);

        String memberId = "dnwo1111";
        Map<String, Object> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("nowDate", nowDate);

        List<ExenditureDto.Response> expenditureList = expenditureRepository.findAllExpenditure(map);
        return expenditureList;
    }

    @Override
    public ExenditureDto.DetailsReponse showMemberDailyExpenditureDetails(ExenditureDto.ExpenditureDetailRequest request) {
        // 해당 날짜 YYYY-MM 보내기
        Map<String, Object> map = new HashMap<>();

        String memberId = "dnwo1111";
        String nowDate = request.getNowDate();

        String expenditureId = request.getExpenditureId();
        map.put("memberId", memberId);
        map.put("nowDate", nowDate);
        map.put("expenditureId", expenditureId);

        ExenditureDto.DetailsReponse expenditureDetail = expenditureRepository.findAllExpenditureDetails(map);
        return expenditureDetail;
    }

    @Override
    public List<ExenditureDto.TotalCount> showTotalExpenditureByMonth(ExenditureDto.TotalExpenditureCountRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("nowDate", request.getNowDate());
        map.put("memberId", "dnwo1111");
        List<ExenditureDto.TotalCount> response = expenditureRepository.findTotalExpenditureByStartAndEndDate(map);
        return response;
    }

    @Override
    public void modifyExpenditureMemo(Map<String, Object> map) {

    }

    @Override
    public List<ExenditureDto.RatioByCategoryResponse> showExpenditureRatioForCategoryByMonth(Map<String, Object> map) {
        List<ExenditureDto.RatioByCategoryResponse>  response = expenditureRepository.findExpenditureRatioForCategoryByMonth(map);
        return response;
    }

    @Override
    public List<Map<String, Object>>  showExpenditureCountForCategoryByMonth(int month) {
        // 현재 날짜 가져오기
        LocalDate currentDate = LocalDate.now();

        // 14개월 전 날짜 계산
        LocalDate fourteenMonthsAgo = currentDate.minusMonths(month+1);

        // YYYY-MM 형식으로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        String formattedDate = fourteenMonthsAgo.format(formatter);

        Map<String, Object> map = new HashMap<>();
        map.put("targetDate", formattedDate);
        map.put("memberId", "dnwo1111");


        List<Map<String, Object>> result = expenditureRepository.findExpenditureCountForCategoryByMonth(map);

        return result;
    }

    @Override
    public Map<String, Object> showTotalExpenditureForMonthAndTimeByYearAndMonth(String yearAndMonth) {
        Map<String, Object> columns = new HashMap<>();
        columns.put("nowDate", yearAndMonth);
        columns.put("memberId", "dnwo1111");

        Map<String, Object> map = expenditureRepository.findTotalExpenditureForMonthAndTimeByYearAndMonth(columns);

        return map;
    }

    @Override
    public Map<String, Object> findAverageExpenditureForMonthByYear(int year) {
        Map<String, Object> map = new HashMap<>();
        map.put("memberId", "dnwo1111");
        map.put("year", year);

        Map<String, Object> m = expenditureRepository.findAverageExpenditureForMonthByYear(map);

        return m;
    }

    @Override
    public int insertExpenditure(List<ExenditureDto.InsertRequest> expenditureList) {
        return 0;
    }
}
