package kr.or.kosa.nux2.domain.virtualmydata.repository;

import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataTransanctionHistoryDto;
import kr.or.kosa.nux2.domain.virtualmydata.mapper.MyDataTransHistoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class MyDataTransHistoryRepository {

    private final MyDataTransHistoryMapper myDataTransHistoryMapper;

    public List<MyDataTransanctionHistoryDto.Response> findAllTransHistory(String memberId, String cardNumber) {
        return myDataTransHistoryMapper.findAllTransHistory(memberId, cardNumber);
    }

    public boolean insertTransHistory(MyDataTransanctionHistoryDto.InsertRequest insertRequest) {
        int result = myDataTransHistoryMapper.insertTransHistory(insertRequest);

        if(result == 1) return true;
        return false;
    }

}
