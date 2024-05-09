package kr.or.kosa.nux2.domain.member.dto;

import java.util.List;

public class MemberRespDto {
    //아이디
    //비밀번호
    //연락처
    //List<소비카테고리>
    //월평균 목표지출액
    String username;
    String password;
    String contact;
    int targetExpenditure;
    List<ExpenditureCategoryDto> expenditureCategoryDtoList;
}
