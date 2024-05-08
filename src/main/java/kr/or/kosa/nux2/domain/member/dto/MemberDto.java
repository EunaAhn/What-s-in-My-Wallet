package kr.or.kosa.nux2.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MemberDto {
    //아이디
    //비밀번호
    //연락처
    //List<소비카테고리>
    //월평균 목표지출액
    String username;
    String password;
    String contact;
    int targetExpenditure;
    List<ExpenditureDto> expenditureDtoList;
}
