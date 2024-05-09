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

    public List<MyDataTransanctionHistoryDto.Response> findAllTransHistory(Long cardId, String cardNumber){
        return myDataTransHistoryMapper.findAllTransHistory(cardId, cardNumber);
    };
    public int insertTransHistory(MyDataTransanctionHistoryDto.InsertRequest insertRequest){
        return myDataTransHistoryMapper.insertTransHistory(insertRequest);
    };


}
