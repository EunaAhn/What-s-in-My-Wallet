package kr.or.kosa.nux2.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import kr.or.kosa.nux2.domain.member.service.MemberService;
import kr.or.kosa.nux2.web.auth.CustomUserDetails;
import kr.or.kosa.nux2.web.common.code.SuccessCode;
import kr.or.kosa.nux2.web.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Tag(name = "Member", description = "Member API")
public class MemberController {
    private final MemberService memberService;


    @Operation(summary = "controller", description = "controller.")
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/oauth")
    public String login(){
        return "login";
    }

    @PostMapping("/logout/1")
    public String logout(HttpServletRequest request,@AuthenticationPrincipal CustomUserDetails customUserDetails){
        HttpSession session = request.getSession();
        session.invalidate();
        return memberService.logout(customUserDetails);
    }
    @Operation(summary = "controller", description = "controller.")
    @PostMapping("/signIn")
    public String signIn(@RequestBody MemberDto.SignInRequest request){
        memberService.signIn(request);
        return "main";
    }
    @PostMapping("/email")
    public ResponseEntity<ApiResponse<MemberDto.checkMemberIdResponse>> checkMemberIdAuthentication(@RequestBody MemberDto.MemberIdRequest request) {
        MemberDto.checkMemberIdResponse response = new MemberDto.checkMemberIdResponse(memberService.checkMemberId(request));
        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }
    @PostMapping("/email/authentication")
    public ResponseEntity<ApiResponse<MemberDto.checkAuthenticationNumberResponse>> checkAuthenticationNumber(@RequestBody MemberDto.AuthenticationRequest request){
        MemberDto.checkAuthenticationNumberResponse response = new MemberDto.checkAuthenticationNumberResponse(memberService.validateAuthenticationNumber(request));
        return new ResponseEntity<>(new ApiResponse<>(response, SuccessCode.SELECT_SUCCESS), HttpStatus.OK);
    }

    @GetMapping("/profile")
    public String editProfile(){

        return "profile";
    }



}
