package kr.or.kosa.nux2.domain.member.mapper;

import kr.or.kosa.nux2.domain.member.dto.MemberConsCategoryDto;
import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MemberExpenditureCategoryMapper {
    //int insertMemberConsCategory(List<MemberConsCategoryDto.MemberConsCategoryIdDto> memberConsCategoryDtoList);
    int insertMemberConsCategory(Map<String,Object> paramMap);
    List<MemberConsCategoryDto.MemberConsCategoryResponse> selectMemberConsCategoryNames(MemberDto.MemberIdRequest request);
    int updateMemberConsCategoryList(Map<String, Object> paramMap);
    List<MemberConsCategoryDto.MemberConsCategoryIdDto> selectMemberConsCategoryIds(Map<String,Object> paramMap);

    int deleteMemberConsCategory(Map<String, Object> paramMap);
}
