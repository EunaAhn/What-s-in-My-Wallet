package kr.or.kosa.nux2.web.controller;

import kr.or.kosa.nux2.domain.expenditure.dto.ExenditureDto;
import kr.or.kosa.nux2.domain.expenditure.service.ExpenditureServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/expenditure")
public class ExpneditureController {
    private final ExpenditureServiceImpl expenditureService;

    @GetMapping("/monthly")
    public String memberMonthlyExpenditures(Model model) {
        List<ExenditureDto.Response> response = expenditureService.showMemberMonthlyExpenditures();
        model.addAttribute("expenditureList", response);

        return "history";
    }
}
