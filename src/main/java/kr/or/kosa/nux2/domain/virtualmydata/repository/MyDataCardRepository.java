package kr.or.kosa.nux2.domain.virtualmydata.repository;

import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataCardDto;
import kr.or.kosa.nux2.domain.virtualmydata.mapper.MyDataCardMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class MyDataCardRepository {

    private final MyDataCardMapper myDataCardMapper;


    public List<MyDataCardDto.Response> findAllMyDataCard(MyDataCardDto.AuthenticationRequest authenticationRequest) {
        return myDataCardMapper.findAllMyDataCardByAuth(authenticationRequest);
    }

    public void insertMyDataCard(MyDataCardDto.InsertRequest insertRequest) {
        myDataCardMapper.insertMyDataCard(insertRequest);
    }

    public List<MyDataCardDto.Response> findAllMyDataCard() {
        return myDataCardMapper.findAllMyDataCard();
    }

    public MyDataCardDto.Response findMyDataCardByCardNumber(String cardNumber) {
        return myDataCardMapper.findMyDataCardByCardNumber(cardNumber);
    }
}
