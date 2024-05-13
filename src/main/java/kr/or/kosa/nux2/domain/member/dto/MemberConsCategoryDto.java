package kr.or.kosa.nux2.domain.member.dto;

import lombok.*;


public class MemberConsCategoryDto {

    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    public static class MemberConsCategoryRequest{
        String memberId;
        String expenditureCategoryId;
    }
    @Setter
    @Getter
    @ToString
    public static class MemberConsCategoryResponse{
        String expenditureCategoryName;
    }
}
