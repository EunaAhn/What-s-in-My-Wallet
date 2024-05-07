package kr.or.kosa.nux2.domain.member.dto;

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
}
