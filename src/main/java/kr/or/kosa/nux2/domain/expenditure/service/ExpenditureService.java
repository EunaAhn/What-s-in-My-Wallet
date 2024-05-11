package kr.or.kosa.nux2.domain.expenditure.service;

import kr.or.kosa.nux2.domain.expenditure.dto.ExenditureDto;
import kr.or.kosa.nux2.domain.member.dto.ExpenditureCategoryDto;

import java.util.List;

public interface ExpenditureService {
    List<ExenditureDto.Response> showMemberMonthlyExpenditures();
    ExenditureDto.DetailsReponse showMemberDailyExpenditureDetails(ExenditureDto.ExpenditureDetailRequest request);
}
