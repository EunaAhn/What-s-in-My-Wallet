package kr.or.kosa.nux2.domain.member.dto;

import java.util.List;

public class UpdateMemberDto {
    String password;
    int targetExpenditure;
    List<ExpenditureCategoryDto> expenditureCategoryDtoList;
}
