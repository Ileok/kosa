package kosaShoppingMall.mapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import kosaShoppingMall.domain.MemberDTO;

@Component
@Repository(value = "kosaShoppingMall.mapper.MemberShipMapper")
public interface MemberShipMapper {
	public MemberDTO selectOne(String memid);
	public Integer memberInsert(MemberDTO dto);
	public Integer memberUpdate(MemberDTO dto);
	public Integer updatePassword(MemberDTO dto);
	public Integer memberDrop(String memId);
}