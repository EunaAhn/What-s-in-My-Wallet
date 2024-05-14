package kr.or.kosa.nux2.domain.virtualmydata.service;

import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataCardDto;

import java.util.List;
import java.util.Map;

public interface MyDataCardService {
    List<MyDataCardDto.Response> showAllMyDataCard(MyDataCardDto.AuthenticationRequest authenticationRequest);

    void createMyDataCard(MyDataCardDto.InsertRequest insertRequest);

    List<MyDataCardDto.Response> showAllMyDataCard();

    MyDataCardDto.Response findMyDataCardByCardNumber(Map<String, Object> map);
}
