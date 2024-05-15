package kr.or.kosa.nux2.domain.virtualmydata.mapper;

import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataCardDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MyDataCardMapper {
    List<MyDataCardDto.Response> findAllMyDataCardByAuth(MyDataCardDto.AuthenticationRequest authenticationRequest);

    int insertMyDataCard(MyDataCardDto.InsertRequest insertRequest);

    List<MyDataCardDto.Response> findAllMyDataCard();

    MyDataCardDto.Response findMyDataCardByCardNumber(Map<String, Object> map);
}