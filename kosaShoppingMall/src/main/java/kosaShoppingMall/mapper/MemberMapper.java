package kosaShoppingMall.mapper;

import java.util.List;

import kosaShoppingMall.domain.MemberDTO;

public interface MemberMapper {

	String numberGenerate();

	void memInsert(MemberDTO dto);

	List<MemberDTO> selectAll();

	MemberDTO selectOne(String memNum);

	Integer memUpdate(MemberDTO dto);

	Integer memDelete(String memNum);

}
