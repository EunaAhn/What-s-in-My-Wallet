package kr.or.kosa.nux2.domain.expenditure.service;

import kr.or.kosa.nux2.domain.expenditure.dto.ExenditureDto;
import kr.or.kosa.nux2.domain.expenditure.repository.ExpenditureRepository;
import kr.or.kosa.nux2.domain.registrationcard.dto.RegistrationCardDto;
import kr.or.kosa.nux2.domain.registrationcard.service.RegistrationCardServiceImpl;
import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataTransanctionHistoryDto;
import kr.or.kosa.nux2.domain.virtualmydata.service.MyDataTransHistorySevice;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExpenditureServiceImpl implements ExpenditureService {
    private final ExpenditureRepository expenditureRepository;
    private final MyDataTransHistorySevice myDataTransHistorySevice;
    private final RegistrationCardServiceImpl registrationCardService;

    /**
     * 지출내역 리스트 출력 함수
     *
     * @param request: 해당연월일, 카테고리(옵션)
     * @return: 지출내역 리스트
     */
    @Override
    public Map<String, Object> showMemberMonthlyExpenditures(ExenditureDto.ByYearAndMonthRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        // 쿼리 검색 map 정의
        Map<String, Object> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("nowDate", request.getYearAndMonth());
        map.put("keyword", request.getKeyWord());

        // 지출내역과 소비카테고리내역 리스트 반환
        List<ExenditureDto.Response> expenditureList = expenditureRepository.findAllExpenditure(map);
        List<ExenditureDto.CategoryList> categoryNames = expenditureRepository.findAllCategoryList(map);

        Map<String, Object> responses = new HashMap<>();
        responses.put("expenditureList", expenditureList);
        responses.put("categoryList", categoryNames);

        return responses;
    }

    /**
     * 지출 내역 상세 조회 함수
     *
     * @param request: 해당일
     * @return: 지출 내역
     */
    @Override
    public ExenditureDto.DetailsReponse showMemberDailyExpenditureDetails(ExenditureDto.ExpenditureDetailRequest request) {
        Map<String, Object> map = new HashMap<>();

        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();
        map.put("memberId", memberId);
        map.put("nowDate", request.getNowDate());
        //map.put("expenditureId", request.getExpenditureId());

        ExenditureDto.DetailsReponse response = expenditureRepository.findAllExpenditureDetails(map);
        return response;
    }

    /**
     * 월별 지출 횟수 출력 함수
     *
     * @param request: 해당년월일
     * @return: 해당월 지출 횟수
     */
    @Override
    public List<ExenditureDto.TotalCount> showTotalExpenditureByMonth(ExenditureDto.ByYearAndMonthRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> map = new HashMap<>();
        map.put("nowDate", request.getYearAndMonth());
        map.put("memberId", memberId);

        List<ExenditureDto.TotalCount> response = expenditureRepository.findTotalExpenditureByStartAndEndDate(map);
        return response;
    }


    /**
     * 카테고리별 지출 비율
     *
     * @param request: 시작시점(1,3,6개월)
     * @return: 카테고리별 지출 비율
     */
    @Override
    public List<ExenditureDto.RatioByCategoryResponse> showExpenditureRatioForCategoryByMonth(ExenditureDto.ByYearAndMonthRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> map = new HashMap<>();
        map.put("month", request.getYearAndMonth());
        map.put("memberId", memberId);

        List<ExenditureDto.RatioByCategoryResponse> response = expenditureRepository.findExpenditureRatioForCategoryByMonth(map);
        return response;
    }

    /**
     * 연도별 월별 지출 금액
     *
     * @param request: 해당연도
     * @return: 해당연도 월별 지출 금액
     */
    @Override
    public List<Map<String, Object>> showExpenditureCountForCategoryByMonth(ExenditureDto.ByYearAndMonthRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> map = new HashMap<>();

        map.put("yearAndMonth", request.getYearAndMonth());
        map.put("memberId", memberId);

        List<Map<String, Object>> response = expenditureRepository.findExpenditureCountForCategoryByMonth(map);
        return response;
    }

    /**
     * 월별 시간대별 총지출액 통계 조회 함수
     *
     * @param request: 해당연월일
     * @return: 월별 시간대별 총지출액
     */
    @Override
    public Map<String, Object> showTotalExpenditureForMonthAndTimeByYearAndMonth(ExenditureDto.ByYearAndMonthRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> columns = new HashMap<>();

        columns.put("nowDate", request.getYearAndMonth());
        columns.put("memberId", memberId);

        Map<String, Object> response = expenditureRepository.findTotalExpenditureForMonthAndTimeByYearAndMonth(columns);
        return response;
    }

    /**
     * 해당연도 월별 지출 금액 조회 함수
     *
     * @param request: 해당연도
     * @return: 월별 지출 금액
     */
    @Override
    public Map<String, Object> findAverageExpenditureForMonthByYear(ExenditureDto.ByYearRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("year", request.getYear());

        Map<String, Object> response = expenditureRepository.findAverageExpenditureForMonthByYear(map);
        return response;
    }

    /**
     * 지출 등록 함수
     *
     * @return: 등록 성공여부
     */
    @Override
    @Transactional
    public boolean insertExpenditure() {
        boolean result = false;
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> map = new HashMap<>();
        map.put("memberId", "dnwo1111@naver.com");
        System.out.println(memberId);
        // 사용자의 등록카드리스트를 조회
        List<RegistrationCardDto.Response> registeredCard = registrationCardService.showAllRegisteredCardByMemberId();

        // 등록카드를 순회
        for (RegistrationCardDto.Response card : registeredCard) {
            // 마이데이터 거래내역에서 등록카드의 거래내역을 조회
            System.out.println(card.getCardNumber());
            List<MyDataTransanctionHistoryDto.Response> transactions = myDataTransHistorySevice.findMemberTransactions(memberId, card.getCardNumber());
            System.out.println(transactions.size());
            if (transactions.size() != 0) {
                // 조회결과를 삽입
                map.put("list", transactions);
                result = expenditureRepository.insertExpenditures(map);
            }
        }

        return result;
    }

    /**
     * 해당 시간대별 소비횟수 조회 함수
     *
     * @param request: 해당연월, 끝시간, 시작시간
     * @return: 해당시간대(낮, 밤시간대) 총 지출 횟수
     */
    @Override
    public ExenditureDto.TotalCount showExpenditureTotalCount(ExenditureDto.TotalCountByTimePeriodRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> map = new HashMap<>();

        map.put("memberId", memberId);
        map.put("yearAndMonth", request.getYearAndMonth());
        map.put("startHour", request.getStartHour());
        map.put("endHour", request.getEndHour());

        ExenditureDto.TotalCount response = expenditureRepository.findExpenditureTotalCount(map);
        return response;
    }

    /**
     * 목표지출금액 대비 지출 조회 함수
     *
     * @param request: 해당연월
     * @return 지출(절약금액) 조회
     */
    @Override
    public ExenditureDto.TendencyAnalysis findExpendiutreTendencyAnalysis(ExenditureDto.ByYearAndMonthRequest request) {
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("yearAndMonth", request.getYearAndMonth());

        ExenditureDto.TendencyAnalysis response = expenditureRepository.findExpendiutreTendencyAnalysis(map);
        return response;
    }

    /**
     * 메모 수정 함수
     *
     * @param request: 메모아이디, 메모 내용
     * @return 성공여부
     */
    @Override
    public boolean updateDailyExpenditureMemo(ExenditureDto.UpdateMemoRequest request) {
        boolean result = false;
        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Map<String, Object> map = new HashMap<>();

        map.put("memoId", request.getMemoId());
        map.put("expenditureMemo", request.getMemo());
        map.put("memberId", memberId);

        if (checkExistMemo(map)) {
            result = expenditureRepository.updateExpenditureMemo(map);
        } else {
            result = expenditureRepository.insertExpenditureMemo(map);
        }
        return result;
    }

    /**
     * 메모가 존재하는지 확인하는 함수
     *
     * @param map: 회원아이디, 메모아이디, 메모내용
     * @return: 메모 있으면 true, 없으면 false
     */
    @Override
    public boolean checkExistMemo(Map<String, Object> map) {
        return expenditureRepository.isExistMemo(map);
    }

    /**
     * 지출리스트 삭제 함수
     *
     * @param cardNumber: 카드번호
     * @return: 성공여부
     */
    @Override
    public boolean deleteExpenditureList(String cardNumber) {
        return expenditureRepository.deleteExpenditure(cardNumber);
    }
}
