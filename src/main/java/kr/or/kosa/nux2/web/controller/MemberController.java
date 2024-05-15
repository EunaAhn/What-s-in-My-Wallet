package kr.or.kosa.nux2.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import kr.or.kosa.nux2.domain.member.service.MemberService;
import kr.or.kosa.nux2.web.auth.principal.CustomUserDetails;
import lombok.RequiredArgsConstructor;
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
    public String index() {
        return "index";
    }

    @GetMapping("/oauth")
    public String login() {
        return "login";
    }

    @PostMapping("/logout/1")
    public String logout(HttpServletRequest request, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        HttpSession session = request.getSession();
        session.invalidate();
        return memberService.logout(customUserDetails);
    }

    @Operation(summary = "controller", description = "controller.")
    @PostMapping("/signUp")
    public String signUp(@RequestBody MemberDto.SignUpRequest request) {
        memberService.signUp(request);
        return "cardlist";
    }
//    @GetMapping("/profile/status=0")
//    public String oAuthMyPage(){
//        return "profile";
//    }


}
