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

    /**
     * 내 카드 목록 조회 함수
     *
     * @param authenticationRequest: 회원이름, 회원아이디(이메일)
     * @return 카드 목록 정보
     */
    @Override
    public List<MyDataCardDto.Response> showAllMyDataCard(MyDataCardDto.AuthenticationRequest authenticationRequest) {
        List<MyDataCardDto.Response> responses = myDataCardRepository.findAllMyDataCard(authenticationRequest);

        return responses;
    }

    /**
     * 카드번호로 해당 카드 찾는 함수
     *
     * @param map: 카드번호
     * @return 해당 카드 정보
     */
    @Override
    public MyDataCardDto.Response findMyDataCardByCardNumber(Map<String, Object> map) {
        MyDataCardDto.Response response = myDataCardRepository.findMyDataCardByCardNumber(map);

        return response;
    }
}
