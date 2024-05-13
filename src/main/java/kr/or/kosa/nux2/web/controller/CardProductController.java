package kr.or.kosa.nux2.web.controller;

import kr.or.kosa.nux2.domain.cardproduct.dto.CardProductDto;
import kr.or.kosa.nux2.domain.cardproduct.service.CardProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cardproduct")
public class CardProductController {
        private final CardProductServiceImpl cardProductService;


//        @GetMapping("/list")
//        public String showCardList(Model model) {
//            List<CardProductDto.Response> response = cardProductService.showCardProductList();
//
//            model.addAttribute("response" , response);
//
//            return "cardlist";
//        }
}
