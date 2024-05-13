package kr.or.kosa.nux2.domain.virtualmydata.mapper;

import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataCardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyDataCardMapper {
    // 회원 dus로 카드목록 조회
    List<MyDataCardDto.Response> findAllMyDataCardByAuth(MyDataCardDto.AuthenticationRequest authenticationRequest);

    //관리자용
    void insertMyDataCard(MyDataCardDto.InsertRequest insertRequest);

    List<MyDataCardDto.Response> findAllMyDataCard();

    MyDataCardDto.Response findMyDataCardByCardNumber(@Param("cardNumber") String cardNumber);
    //int deleteMyDataCardByCardNum(String cardNumber, String userName);
}