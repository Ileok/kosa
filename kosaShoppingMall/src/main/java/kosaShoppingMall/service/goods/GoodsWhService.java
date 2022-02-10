package kosaShoppingMall.service.goods;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosaShoppingMall.command.GoodsWhCommand;
import kosaShoppingMall.domain.GoodsWhDTO;
import kosaShoppingMall.mapper.GoodsMapper;
@Service
public class GoodsWhService {
	@Autowired
	GoodsMapper goodsMapper;
	public void execute(GoodsWhCommand goodsWhCommand) {
		GoodsWhDTO dto = new GoodsWhDTO();
		dto.setGoodsNum(goodsWhCommand.getGoodsNum());
		dto.setWhDate(goodsWhCommand.getWhDate());
		dto.setWhQty(goodsWhCommand.getWhQty());
		dto.setMadeDate(
				Timestamp.valueOf(goodsWhCommand.getMadeDate()));
		Integer i = goodsMapper.whInsert(dto);
		System.out.println(i + "개 행이(가) 삽입되었습니다.");
	}
}