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

    public List<MyDataTransanctionHistoryDto.Response> findMemberTransactions(String memberId, String cardNumber) {
        List<MyDataTransanctionHistoryDto.Response> response = myDataTransHistoryRepository.findAllTransHistory(memberId, cardNumber);
        return response;
    }

}
