package kr.or.kosa.nux2.web.restcontroller;

import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import kr.or.kosa.nux2.domain.member.service.MemberService;
import kr.or.kosa.nux2.web.common.code.SuccessCode;
import kr.or.kosa.nux2.web.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberRestController {

    private final MemberService memberService;

    @PostMapping("/email")
    public ResponseEntity<ApiResponse<MemberDto.CheckMemberIdResponse>> checkMemberIdAuthentication(@RequestBody MemberDto.MemberIdRequest request) {
        MemberDto.CheckMemberIdResponse response = new MemberDto.CheckMemberIdResponse(memberService.checkMemberId(request));
        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }
    @PostMapping("/email/authentication")
    public ResponseEntity<ApiResponse<MemberDto.CheckAuthenticationNumberResponse>> checkAuthenticationNumber(@RequestBody MemberDto.AuthenticationDto request){
        MemberDto.CheckAuthenticationNumberResponse response = new MemberDto.CheckAuthenticationNumberResponse(memberService.validateAuthenticationNumber(request));
        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }
    @PostMapping("/api/member/profile")
    public ResponseEntity<ApiResponse<MemberDto.ProfileResponse>> profile(){
        MemberDto.ProfileResponse response = memberService.showMemberProfile();
        System.out.println("response"+response);
        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @PostMapping("/api/member/profile/edit")
    public ResponseEntity<ApiResponse<MemberDto.ProfileResponse>> editMemberInfo(@RequestBody MemberDto.UpdateMemberInfoRequest request){
        MemberDto.ProfileResponse response = memberService.updateMemberInfo(request);
        System.out.println("response"+response);
        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @PostMapping("/api/member/profile/password")
    public boolean editPassword(@RequestBody MemberDto.UpdatePasswordRequest request){
        return memberService.updatePassword(request);
    }

}
