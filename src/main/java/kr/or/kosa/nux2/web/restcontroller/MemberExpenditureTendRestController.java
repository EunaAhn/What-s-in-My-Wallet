package kr.or.kosa.nux2.web.restcontroller;

import kr.or.kosa.nux2.domain.memberexpendituretend.dto.MemberExpenditureTendDto;
import kr.or.kosa.nux2.domain.memberexpendituretend.service.MemberExpenditureTendServiceImpl;
import kr.or.kosa.nux2.web.common.code.SuccessCode;
import kr.or.kosa.nux2.web.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/expendituretendency")
public class MemberExpenditureTendRestController {
    private final MemberExpenditureTendServiceImpl memberExpenditureTendService;


    @PostMapping("/")
    public ResponseEntity<ApiResponse<List<MemberExpenditureTendDto.Response>>> showExpenditureTend(@RequestBody MemberExpenditureTendDto.ExpenditureTendRequest request) {
        List<MemberExpenditureTendDto.Response> responses =  memberExpenditureTendService.findMemberExpenditureTend(request.getYearAndMonth());
       return new ResponseEntity<>(new ApiResponse<>(responses, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }
}
