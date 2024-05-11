package kr.or.kosa.nux2.web.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.or.kosa.nux2.domain.member.service.MemberService;
import kr.or.kosa.nux2.web.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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


}
