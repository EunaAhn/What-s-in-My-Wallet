package kr.or.kosa.nux2.web.restcontroller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.or.kosa.nux2.domain.cardrecommand.dto.CardRecommandDto;
import kr.or.kosa.nux2.domain.cardrecommand.service.CardRecommadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cardreco")
@Tag(name = "CardRecommand", description = "카드추천 Rest API")
public class CardRecoRestController {
    private final CardRecommadService cardRecommadService;

    @Operation(summary = "카드 추천 ", description = "해당년월을 기준으로 카드 추천")
    @PostMapping("/list")
    public ResponseEntity<CardRecommandDto.CardRecommandResponse> recommandCardReco(@RequestBody CardRecommandDto.YearAndMonthRequest request) {
        CardRecommandDto.CardRecommandResponse response = cardRecommadService.recommandCards(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
