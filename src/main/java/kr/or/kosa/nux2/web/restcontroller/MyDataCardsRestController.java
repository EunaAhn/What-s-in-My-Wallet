package kr.or.kosa.nux2.web.restcontroller;

import kr.or.kosa.nux2.domain.virtualmydata.dto.MyDataCardDto;
import kr.or.kosa.nux2.domain.virtualmydata.repository.MyDataCardRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MyDataCardsRestController {
    private final MyDataCardRepository myDataCardRepository;

    @GetMapping("/mydatatest")
    public ResponseEntity<?> test(){

        MyDataCardDto.AuthenticationRequest auth = new MyDataCardDto.AuthenticationRequest("김우재", "01089387607");
          //List<MyDataCardDto.Response> response = myDataCardRepository.findAllMyDataCard(auth);
          //myDataCardRepository.insertMyDataCard(new MyDataCardDto.InsertRequest("1111111111111111","111", "정혜미", "8", "30", "01099999999","10"));

        List<MyDataCardDto.Response> response = myDataCardRepository.findAllMyDataCard();

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }
}
