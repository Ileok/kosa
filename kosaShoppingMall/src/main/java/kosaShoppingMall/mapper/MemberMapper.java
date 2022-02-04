package kosaShoppingMall.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import kosaShoppingMall.domain.MemberDTO;

@Repository(value = "kosaShoppingMall.mapper.MemberMapper")
public interface MemberMapper {
	public String numberGenerate();
	public Integer memInsert(MemberDTO dto);
	public List<MemberDTO> selectAll();
	public MemberDTO selectOne(String memberNum);
	public Integer memUpdate(MemberDTO dto);
	public Integer memDelete(String memberNum);
}