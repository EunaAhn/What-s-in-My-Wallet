package kr.or.kosa.nux2.domain.virtualmydata.mapper;

import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataCardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyDataCardMapper {
    // 회원 주민등록번호로 카드목록 조회
    List<MyDataCardDto.Response> findAllMyDataCard(MyDataCardDto.authenticationRequest authenticationRequest);

    //관리자용
    void insertMyDataCard(MyDataCardDto.InsertRequest insertRequest);
    List<MyDataCardDto.Response> findAllMyDataCard();
    //int deleteMyDataCardByCardNum(String cardNumber, String userName);
}
