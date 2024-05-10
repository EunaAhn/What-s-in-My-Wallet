package kr.or.kosa.nux2.domain.virtualmydata.repository;

import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataCardDto;
import kr.or.kosa.nux2.domain.virtualmydata.mapper.MyDataCardMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class MyDataCardRepository {
    private final MyDataCardMapper myDataCardRepository;

    List<MyDataCardDto.Response> findAllMyDataCard(MyDataCardDto.authenticationRequest authenticationRequest){
        return myDataCardRepository.findAllMyDataCard(authenticationRequest);
    };

    void insertMyDataCard(MyDataCardDto.InsertRequest insertRequest){
        myDataCardRepository.insertMyDataCard(insertRequest);
    };

    List<MyDataCardDto.Response> findAllMyDataCard(){
        return myDataCardRepository.findAllMyDataCard();
    }

}
