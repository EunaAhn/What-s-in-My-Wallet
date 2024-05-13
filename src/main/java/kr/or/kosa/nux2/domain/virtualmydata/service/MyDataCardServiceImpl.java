package kr.or.kosa.nux2.domain.virtualmydata.service;

import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataCardDto;
import kr.or.kosa.nux2.domain.virtualmydata.repository.MyDataCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyDataCardServiceImpl implements MyDataCardService{
    private final MyDataCardRepository myDataCardRepository;
    @Override
    public List<MyDataCardDto.Response> showAllMyDataCard(MyDataCardDto.AuthenticationRequest authenticationRequest) {
        List<MyDataCardDto.Response> responses = myDataCardRepository.findAllMyDataCard(authenticationRequest);
        return responses;
    }




    @Override
    public void createMyDataCard(MyDataCardDto.InsertRequest insertRequest) {
        // 관리자용 후순위
    }

    @Override
    public List<MyDataCardDto.Response> showAllMyDataCard() {
        // 관리자용 후순위
        return List.of();
    }

    @Override
    public MyDataCardDto.Response findMyDataCardByCardNumber(String cardNumber) {
        MyDataCardDto.Response response = myDataCardRepository.findMyDataCardByCardNumber(cardNumber);
        return response;
    }
}
