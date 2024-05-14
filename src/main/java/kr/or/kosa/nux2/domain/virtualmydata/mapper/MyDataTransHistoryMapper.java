package kr.or.kosa.nux2.domain.virtualmydata.mapper;

import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataTransanctionHistoryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MyDataTransHistoryMapper {
    //마이데이터 거래내역은 결제 시도 시 insert된다.
    //update되는 내용은 아님.
    // 삭제되는 내용도 아님.
    // 즉, select, insert 용 테이블

    // 아이디보다 큰 아이디 값, 카드번호
    public List<MyDataTransanctionHistoryDto.Response> findAllTransHistory(@Param("memberId") String memberId, @Param("cardNumber") String cardNumber);

    public int insertTransHistory(MyDataTransanctionHistoryDto.InsertRequest insertRequest);
}
