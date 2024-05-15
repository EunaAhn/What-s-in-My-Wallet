package kr.or.kosa.nux2.domain.virtualmydata.repository;

import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataCardDto;
import kr.or.kosa.nux2.domain.virtualmydata.mapper.MyDataCardMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@AllArgsConstructor
public class MyDataCardRepository {

    private final MyDataCardMapper myDataCardMapper;


    public List<MyDataCardDto.Response> findAllMyDataCard(MyDataCardDto.AuthenticationRequest authenticationRequest) {
        return myDataCardMapper.findAllMyDataCardByAuth(authenticationRequest);
    }

    public boolean insertMyDataCard(MyDataCardDto.InsertRequest insertRequest) {
        int result = myDataCardMapper.insertMyDataCard(insertRequest);

        if(result == 1) return true;
        return false;
    }

    public List<MyDataCardDto.Response> findAllMyDataCard() {
        return myDataCardMapper.findAllMyDataCard();
    }

    public MyDataCardDto.Response findMyDataCardByCardNumber(Map<String, Object> map) {
        return myDataCardMapper.findMyDataCardByCardNumber(map);
    }
}
