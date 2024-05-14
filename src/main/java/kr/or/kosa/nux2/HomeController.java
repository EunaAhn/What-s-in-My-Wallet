package kr.or.kosa.nux2;

import jakarta.servlet.http.HttpServletRequest;
import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/onboarding", method= RequestMethod.GET)
    public String goOnBoarding(HttpServletRequest request) {
        return "onboarding";
    }

    @RequestMapping(value = "/login", method= RequestMethod.GET)
    public String goLogin(HttpServletRequest request) {return "login";}

    @RequestMapping(value = "/signup", method= RequestMethod.GET)
    public String goSignup(HttpServletRequest request, Model model) {
        MemberDto.SignInRequest r = new MemberDto.SignInRequest();
        model.addAttribute("model", r);
        return "signup";
    }

    @RequestMapping(value = "/cardlist", method= RequestMethod.GET)
    public String goCardlist(HttpServletRequest request) {
        return "cardlist";
    }

    @RequestMapping(value = "/history", method= RequestMethod.GET)
    public String goHistory(HttpServletRequest request) {
        return "history";
    }

    @RequestMapping(value = "/analyze", method= RequestMethod.GET)
    public String goAnalyze(HttpServletRequest request) {
        return "analyze";
    }

    @RequestMapping(value = "/suggestion", method= RequestMethod.GET)
    public String goSuggestion(HttpServletRequest request) {
        return "suggestion";
    }

//    @RequestMapping(value = "/profile", method= RequestMethod.GET)
//    public String goProfile(HttpServletRequest request) {
//        return "profile";
//    }

    @RequestMapping(value = "/carddetail", method= RequestMethod.GET)
    public String goCarddetail(HttpServletRequest request) {
        return "carddetail";
    }

    @RequestMapping(value = "/cardregistration", method= RequestMethod.GET)
    public String goCardregistration(HttpServletRequest request) {
        return "cardregistration";
    }

}