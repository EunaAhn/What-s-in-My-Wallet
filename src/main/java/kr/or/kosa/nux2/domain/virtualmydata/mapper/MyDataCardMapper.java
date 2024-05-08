package kr.or.kosa.nux2.domain.virtualmydata.mapper;

import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataCardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyDataCardMapper {
    // 회원 주민등록번호로 카드목록 조회
    List<MyDataCardDto> findAllMyDataCardByRegNum(String reg_num);
    //관리자용
    void insertMyDataCard(MyDataCardDto myDataCardDto);
    List<MyDataCardDto> findAllMyDataCard();
    int deleteMyDataCardByCardNum(String cardNumber);
    // update는 없음


}
