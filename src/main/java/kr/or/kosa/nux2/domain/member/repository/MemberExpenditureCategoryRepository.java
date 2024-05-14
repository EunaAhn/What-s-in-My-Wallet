package kr.or.kosa.nux2.domain.member.repository;

import kr.or.kosa.nux2.domain.member.dto.MemberConsCategoryDto;
import kr.or.kosa.nux2.domain.member.dto.MemberDto;
import kr.or.kosa.nux2.domain.member.mapper.MemberExpenditureCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class MemberExpenditureCategoryRepository {
    private final MemberExpenditureCategoryMapper memberExpenditureCategoryMapper;

    public int insertMemberConsCategory(Map<String, Object> paramMap) {
        return memberExpenditureCategoryMapper.insertMemberConsCategory(paramMap);
    }

    public List<MemberConsCategoryDto.MemberConsCategoryResponse> selectMemberConsCategoryNames(MemberDto.MemberIdRequest request) {
        return memberExpenditureCategoryMapper.selectMemberConsCategoryNames(request);
    }

    public int updateMemberConsCategoryList(Map<String, Object> paramMap) {
        return memberExpenditureCategoryMapper.updateMemberConsCategoryList(paramMap);
    }

    public List<MemberConsCategoryDto.MemberConsCategoryIdDto> selectMemberConsCategoryIds(Map<String, Object> paramMap) {
        return memberExpenditureCategoryMapper.selectMemberConsCategoryIds(paramMap);
    }

    public int deleteMemberConsCategory(Map<String, Object> paramMap) {
        return memberExpenditureCategoryMapper.deleteMemberConsCategory(paramMap);
    }
}
