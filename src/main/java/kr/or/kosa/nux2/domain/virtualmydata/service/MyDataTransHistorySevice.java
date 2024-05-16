package kr.or.kosa.nux2.domain.virtualmydata.service;

import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataTransanctionHistoryDto;
import kr.or.kosa.nux2.domain.virtualmydata.repository.MyDataTransHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyDataTransHistorySevice {
    private final MyDataTransHistoryRepository myDataTransHistoryRepository;

    /**
     * 회원이 가진 카드의 거래내역 조회 함수
     *
     * @param memberId:   회원아이디(이메일)
     * @param cardNumber: 카드번호
     * @return: 거래내역 정보
     */
    public List<MyDataTransanctionHistoryDto.Response> findMemberTransactions(String memberId, String cardNumber) {
        List<MyDataTransanctionHistoryDto.Response> response = myDataTransHistoryRepository.findAllTransHistory(memberId, cardNumber);

        return response;
    }

}
