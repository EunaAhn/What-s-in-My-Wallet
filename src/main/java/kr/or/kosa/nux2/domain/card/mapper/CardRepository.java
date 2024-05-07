package kr.or.kosa.nux2.domain.card.mapper;

import kr.or.kosa.nux2.domain.card.dto.CardRespDto;
import kr.or.kosa.nux2.domain.card.dto.LikeCardRespDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CardRepository {
    // update 는 무조건 int를 반환하도록 막아둠
    // insert는 int혹은 void를 반환하는 것이 일반적
    // select 는 dto객체 혹은 dto의 컬렉션 객체를 반환한다.
    // select 와 insert의 파라미터값은 ? -> dto 혹은


    // map을 파라미터 값으로 전달하는 경우 동적쿼리를 생성할 수 있다.
    public List<CardRespDto> findAllCardList(Map<String, Object> columns);
    // company, cardname, benefit 3개의 인자로 동적쿼리를 생성해야한다.
    // 카드 목록 필터 기능
    public List<CardRespDto> findTopLikeCardList();

    // 관심 카드 목록 가져오기 (마이페이지)




//    public CardRespDto findAllCardListByCompany();
//    public CardRespDto findAllCardListByCardName();
//    public CardRespDto findAllCardListByBenefit();

}
