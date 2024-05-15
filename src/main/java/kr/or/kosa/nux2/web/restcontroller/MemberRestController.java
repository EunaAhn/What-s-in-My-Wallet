package kr.or.kosa.nux2.web.restcontroller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Member", description = "회원 Rest API")
public class MemberRestController {

    private final MemberService memberService;
    @Operation(summary = "인증번호 이메일 발송", description = "회원테이블에 이메일이 있는지 판단하고, 없을 때 인증번호 이메일 발송")
    @PostMapping("/email")
    public ResponseEntity<ApiResponse<MemberDto.CheckMemberIdResponse>> checkMemberIdAuthentication(@RequestBody MemberDto.MemberIdRequest request) {
        MemberDto.CheckMemberIdResponse response = new MemberDto.CheckMemberIdResponse(memberService.checkMemberId(request));
        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }
    @Operation(summary = "인증번호 검증", description = "이메일에 있는 인증번호와 입력된 값이 같은지 검증")
    @PostMapping("/email/authentication")
    public ResponseEntity<ApiResponse<MemberDto.CheckAuthenticationNumberResponse>> checkAuthenticationNumber(@RequestBody MemberDto.AuthenticationRequest request) {
        MemberDto.CheckAuthenticationNumberResponse response = new MemberDto.CheckAuthenticationNumberResponse(memberService.validateAuthenticationNumber(request));
        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }
    @Operation(summary = "마이페이지 프로필", description = "해당 회원의 저장된 정보 출력")
    @PostMapping("/api/member/profile")
    public ResponseEntity<ApiResponse<MemberDto.ProfileResponse>> profile() {
        MemberDto.ProfileResponse response = memberService.showMemberProfile();
        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }
    @Operation(summary = "마이페이지 프로필 수정", description = "회원의 관심카테고리와 월목표지출액 변경")
    @PostMapping("/api/member/profile/edit")
    public ResponseEntity<ApiResponse<MemberDto.ProfileResponse>> editMemberInfo(@RequestBody MemberDto.UpdateMemberInfoRequest request) {
        MemberDto.ProfileResponse response = memberService.updateMemberInfo(request);
        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }
    @Operation(summary = "마이페이지 비밀번호 변경", description = "회원의 변경할 비밀번호와 확인 비밀번호가 일치하면 변경")
    @PostMapping("/api/member/profile/password")
    public boolean editPassword(@RequestBody MemberDto.UpdatePasswordRequest request) {
        return memberService.updatePassword(request);
    }

}
