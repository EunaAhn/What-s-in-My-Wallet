package kr.or.kosa.nux2.domain.virtualmydata.mapper;

import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataTransanctionHistoryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MyDataTransHistoryMapper {
    List<MyDataTransanctionHistoryDto.Response> findAllTransHistory(@Param("memberId") String memberId, @Param("cardNumber") String cardNumber);
    int insertTransHistory(MyDataTransanctionHistoryDto.InsertRequest insertRequest);
}
