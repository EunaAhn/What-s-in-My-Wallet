package kr.or.kosa.nux2.web.restcontroller;

import kr.or.kosa.nux2.domain.cardrecommand.dto.CardRecommandDto;
import kr.or.kosa.nux2.domain.cardrecommand.service.CardRecommadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cardreco")
public class CardRecoRestController {
    private final CardRecommadService cardRecommadService;

    @PostMapping("/list")
    public ResponseEntity<CardRecommandDto.CardRecommandResponse> recommandCardReco(@RequestBody  CardRecommandDto.YearAndMonthRequest request) {
        CardRecommandDto.CardRecommandResponse response = cardRecommadService.recommandCards(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
