package kosaShoppingMall.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import kosaShoppingMall.command.GoodsWhCommand;
import kosaShoppingMall.domain.GoodsDTO;
import kosaShoppingMall.domain.GoodsWhDTO;
import kosaShoppingMall.domain.GoodsWhGoodsDTO;

@Component
@Repository(value = "kosaShoppingMall.mapper.GoodsMapper")
public interface GoodsMapper {
	public String numberGenerate();

	public Integer goodsInsert(GoodsDTO dto);

	public List<GoodsDTO> selectAll();

	public GoodsDTO selectOne(String goodsNum);

	public void visitCnt(String goodsNum);

	public Integer goodsUpdate(GoodsDTO dto);

	public Integer goodsDelete(String goodsNum);

	public List<GoodsDTO> searchGoods(String goodsWord);

	public List<GoodsDTO> goodsItems(String goodsName);

	public Integer whInsert(GoodsWhDTO dto);

	public List<GoodsWhDTO> whSelect();

	/// 1 대 1 정보
	public GoodsWhGoodsDTO whDetail(GoodsWhDTO idto);

	public GoodsDTO getGoodsWhInfo(GoodsWhDTO idto);

	public Integer goodsWhUpdate(GoodsWhDTO dto);

	public Integer goodsWhDelete(GoodsWhCommand goodsWhCommand);

	// 배열을 이용한 방법
	public Integer goodsDels(String[] deletes);

	// 리스트를 이용한 방법
	public Integer goodsDeletes(List<String> cs);

	// Map을 사용한 방법
	public Integer goodsRemove(Map<String, Object> condition);
}