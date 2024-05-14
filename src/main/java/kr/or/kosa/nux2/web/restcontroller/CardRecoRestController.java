package kr.or.kosa.nux2.web.restcontroller;

import kr.or.kosa.nux2.domain.cardrecommand.dto.CardRecommandDto;
import kr.or.kosa.nux2.domain.cardrecommand.service.CardRecommadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cardreco")
public class CardRecoRestController {
    private final CardRecommadService cardRecommadService;

    @GetMapping("/list")
    public ResponseEntity<CardRecommandDto.CardRecommandResponse> recommandCardReco() {
        CardRecommandDto.CardRecommandResponse response = cardRecommadService.recommandCards();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
