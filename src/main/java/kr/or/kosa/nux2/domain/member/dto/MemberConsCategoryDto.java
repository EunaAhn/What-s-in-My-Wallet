package kr.or.kosa.nux2.domain.member.dto;

import lombok.*;

public class MemberConsCategoryDto {

    @NoArgsConstructor
    @Getter
    @Setter
    public static class MemberConsCategoryIdDto {
        String expenditureCategoryId;
    }

    /*
      ========================================================================================
                                            RESPONSE DTO
      ========================================================================================
     */
    @Getter
    public static class MemberConsCategoryResponse {
        String expenditureCategoryName;
    }
}
