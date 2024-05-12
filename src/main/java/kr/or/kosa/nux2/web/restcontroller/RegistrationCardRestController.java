package kr.or.kosa.nux2.web.restcontroller;

import kr.or.kosa.nux2.domain.registrationcard.dto.RegistrationCardDto;
import kr.or.kosa.nux2.domain.registrationcard.service.RegistrationCardServiceImpl;
import kr.or.kosa.nux2.web.common.code.SuccessCode;
import kr.or.kosa.nux2.web.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registeredcard")

public class RegistrationCardRestController {
    private final RegistrationCardServiceImpl registrationCardService;


    @GetMapping("/list")
    public ResponseEntity<ApiResponse<?>> registeredCard() {
        String memberId= "dnwo1111";
        List<RegistrationCardDto.Response>  responses =  registrationCardService.showAllRegisteredCardByMemberId(memberId);
        return new ResponseEntity<>(new ApiResponse<>(responses, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }


    @GetMapping("/delete")
    public  ResponseEntity<ApiResponse<?>> deleteCard(@RequestBody RegistrationCardDto.InsertControllerRequest request) {
        registrationCardService.deleteRegistrationCard(request.getCardNumber());

        return null;
    }

    @GetMapping("/register")
    public ResponseEntity<?> registerCard(@RequestBody List<RegistrationCardDto.InsertControllerRequest> requests) {
        registrationCardService.insertRegistrationCard(requests);


        return null;
    }
}
