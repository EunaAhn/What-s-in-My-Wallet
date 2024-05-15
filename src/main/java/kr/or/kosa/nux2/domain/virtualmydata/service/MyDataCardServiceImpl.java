package kr.or.kosa.nux2.domain.virtualmydata.service;

import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataCardDto;
import kr.or.kosa.nux2.domain.virtualmydata.repository.MyDataCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MyDataCardServiceImpl implements MyDataCardService {
    private final MyDataCardRepository myDataCardRepository;

    @Override
    public List<MyDataCardDto.Response> showAllMyDataCard(MyDataCardDto.AuthenticationRequest authenticationRequest) {
        List<MyDataCardDto.Response> responses = myDataCardRepository.findAllMyDataCard(authenticationRequest);

        return responses;
    }

    @Override
    public MyDataCardDto.Response findMyDataCardByCardNumber(Map<String, Object> map) {
        MyDataCardDto.Response response = myDataCardRepository.findMyDataCardByCardNumber(map);

        return response;
    }
}
