package kr.or.kosa.nux2.domain.member.dto;


import kr.or.kosa.nux2.domain.expenditure.dto.ExpenditureCategory;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberDto {
    private String memberId;
    private String memberName;
    private String memberContactNumber;
    private String memberPassword;
    private Long targetExpenditure;
    List<ExpenditureCategory> expenditureCategoryList;
}
